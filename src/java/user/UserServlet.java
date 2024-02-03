package user;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UserServlet", urlPatterns = { "/users" })
public class UserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        String alert = "";

        if (action == null || action.equals("/")) {
            // Get all users
            if (request.getSession() != null && request.getSession().getAttribute("token") != null) {
                request.getSession().setAttribute("action", "user");
                request.getSession().setAttribute("title", "User");

                List<User> userList = userDAO.getAll();
                request.setAttribute("userList", userList);
            } else {
                alert = "?error=You must be logged in to view this page.";
            }

            request.getRequestDispatcher("index.jsp" + alert).forward(request, response);
        } else {
            String[] pathParts = action.split("/");
            int userId = Integer.parseInt(pathParts[1]);

            if (pathParts.length == 2) {
                // Get user by ID
                User user = userDAO.getById(userId);
                request.setAttribute("user", user);
            } else if (pathParts.length == 3 && pathParts[2].equals("delete")) {
                // Delete user by ID
                userDAO.delete(userId);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            // Add user
            User newUser = new User();
            newUser.setUsername(request.getParameter("username"));
            userDAO.add(newUser);
        } else {
            String[] pathParts = action.split("/");
            int userId = Integer.parseInt(pathParts[1]);

            if (pathParts.length == 2) {
                // Update user
                User existingUser = userDAO.getById(userId);
                existingUser.setUsername(request.getParameter("username"));
                userDAO.update(existingUser);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "User Servlet: handles user-related requests";
    }
}
