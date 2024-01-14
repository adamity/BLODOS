import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/RouteServlet"})
public class RouteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the action parameter from the request
        String action = request.getParameter("action");
        String title = action.substring(0, 1).toUpperCase() + action.substring(1).replace("-", " ");
        String alert = "";

        // Set the action as a session attribute if token session attribute is not null
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("token") != null) {
            session.setAttribute("action", action);
            session.setAttribute("title", title);
        } else {
            alert = "?error=You must be logged in to view this page.";
        }

        // Redirect the user to the index page, it will load the correct page based on the action session attribute
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
        return "RouteServlet: This servlet is used to route the user to the correct page.";
    }
}
