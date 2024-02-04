package user;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UserServlet", urlPatterns = { "/users/*" })
public class UserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    /**
     * Endpoints Structure:
     * 
     * GET /users
     * - Get all users
     * - Redirect to index.jsp
     * 
     * GET /users/{id}
     * - Get user by ID
     * - Return JSON
     * 
     * GET /users/{id}/delete
     * - Delete user by ID
     * - Redirect to GET /users
     * 
     * POST /users/
     * - Add user
     * - Redirect to GET /users
     * 
     * POST /users/{id}
     * - Update user by ID
     * - Redirect to GET /users
     */

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
            // This section will be requested by AJAX, so we don't need to forward to a JSP page but instead return JSON
            String[] pathParts = action.split("/");
            int userId = Integer.parseInt(pathParts[1]);

            if (pathParts.length == 2) {
                // Get user by ID
                User user = userDAO.getById(userId);
                response.setContentType("application/json");
                response.getWriter().print(user.toJSON());
            } else if (pathParts.length == 3 && pathParts[2].equals("delete")) {
                // Delete user by ID
                userDAO.delete(userId);
                response.sendRedirect(request.getContextPath() + "/users");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            // Add user
            User newUser = new User(
                request.getParameter("role"),
                request.getParameter("firstname"),
                request.getParameter("lastname"),
                request.getParameter("username"),
                request.getParameter("password")
            );

            userDAO.add(newUser);
            response.sendRedirect(request.getContextPath() + "/users");
        } else {
            String[] pathParts = action.split("/");
            int userId = Integer.parseInt(pathParts[1]);

            if (pathParts.length == 2) {
                // Update user
                User user = new User(
                    userId,
                    request.getParameter("role"),
                    request.getParameter("firstname"),
                    request.getParameter("lastname"),
                    request.getParameter("username"),
                    request.getParameter("password")
                );

                userDAO.update(user);
                response.sendRedirect(request.getContextPath() + "/users");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "User Servlet: handles CRUD operations for users";
    }
}
