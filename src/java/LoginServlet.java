import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get credentials from the request
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String alert = "";

        if (username.isEmpty() || password.isEmpty()) {
            response.sendRedirect("index.jsp?error=Please fill all the fields");
            return;
        }

        String dummyUsername = "admin";
        String dummyPassword = "admin";
        String dummyRole = Math.random() < 0.5 ? "admin" : "staff"; // Randomly assign a role

        if (username.equals(dummyUsername) && password.equals(dummyPassword)) {
            HttpSession session = request.getSession();
            if (session != null) {
                String token = java.util.UUID.randomUUID().toString();
                session.setAttribute("token", token);
                session.setAttribute("role", dummyRole);
                session.setAttribute("action", "dashboard");
                session.setAttribute("title", "Dashboard");
                alert = "?success=You have successfully logged in";
            }
        } else {
            alert = "?error=Invalid credentials";
        }

        response.sendRedirect("index.jsp" + alert);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Login Servlet: Handles login requests";
    }
}
