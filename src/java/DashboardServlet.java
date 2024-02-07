import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DashboardServlet", urlPatterns = { "/dashboard" })
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String alert = "";
        if (request.getSession() != null && request.getSession().getAttribute("token") != null) {
            request.getSession().setAttribute("action", "dashboard");
            request.getSession().setAttribute("title", "Dashboard");

            request.setAttribute("totalDonor", "100");
            request.setAttribute("totalEligibleDonor", "70");
            request.setAttribute("totalIneligibleDonor", "30");
            request.setAttribute("totalDonation", "100");
            request.setAttribute("topDonatedBloodType", "A+");
            request.setAttribute("topDonationTypes", List.of("Whole Blood", "Platelets", "Plasma"));
        } else {
            alert = "?error=You must be logged in to view this page.";
        }

        request.getRequestDispatcher("index.jsp" + alert).forward(request, response);
    }
}
