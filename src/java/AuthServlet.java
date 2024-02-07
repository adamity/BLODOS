import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.*;
import utility.*;

@WebServlet(name = "AuthServlet", urlPatterns = { "/auth/*" })
public class AuthServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    /**
     * Endpoints Structure:
     * 
     * POST /auth/login
     * - Login user
     * - Redirect to GET /dashboard
     * 
     * GET /auth/logout
     * - Logout user
     * - Redirect to GET /index
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        String[] pathParts = action.split("/");

        if (pathParts.length == 2 && pathParts[1].equals("logout")) {
            logout(request, response);
        } else {
            response.sendRedirect("../index.jsp?error=Invalid request");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        String[] pathParts = action.split("/");

        if (pathParts.length == 2 && pathParts[1].equals("login")) {
            login(request, response);
        } else {
            response.sendRedirect("../index.jsp?error=Invalid request");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        response.sendRedirect("../index.jsp");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String alert = "";

        try {
            // Get credentials from the request
            String username = request.getParameter("username").trim();
            String password = request.getParameter("password").trim();

            if (username.isEmpty() || password.isEmpty()) {
                alert = "?error=Please fill all the fields";
            } else {
                User userLogin = userDAO.getByCredential(username, password);
                if (userLogin.isValid() && request.getSession() != null) {
                    String token = Encryptor.encrypt(String.valueOf(userLogin.getId()));
                    request.getSession().setAttribute("token", token);
                    request.getSession().setAttribute("role", userLogin.getRole());
                    request.getSession().setAttribute("action", "dashboard");
                    request.getSession().setAttribute("title", "Dashboard");
                    alert = "?success=You have successfully logged in";
                    response.sendRedirect(request.getContextPath() + "/dashboard" + alert);
                } else {
                    alert = "?error=Invalid credentials";
                    response.sendRedirect("../index.jsp" + alert);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Auth Servlet: handles authentication requests";
    }
}
