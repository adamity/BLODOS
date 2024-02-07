import java.io.IOException;
import java.util.List;

import javax.print.DocFlavor.STRING;

import donor.*;
import eligibility.*;
import donation.*;
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

            DonorDAO donorDAO = new DonorDAO();
            List<Donor> donorList = donorDAO.getAll();

            DonationDAO donationDAO = new DonationDAO();
            List<Donation> donationList = donationDAO.getAll();
            String topDonatedBloodType = donationDAO.getTopDonatedBloodType();
            List<String> topDonationTypes = donationDAO.getTopDonationTypes();

            EligibilityDAO eligibilityDAO = new EligibilityDAO();
            List<Eligibility> eligibileList = eligibilityDAO.getEligibleDonors();
            List<Eligibility> ineligibileList = eligibilityDAO.getIneligibleDonors();

            request.setAttribute("totalDonor", String.valueOf(donorList.size()));
            request.setAttribute("totalEligibleDonor", String.valueOf(eligibileList.size()));
            request.setAttribute("totalIneligibleDonor", String.valueOf(ineligibileList.size()));
            request.setAttribute("totalDonation", String.valueOf(donationList.size()));
            request.setAttribute("topDonatedBloodType", topDonatedBloodType);
            request.setAttribute("topDonationTypes", topDonationTypes);
        } else {
            alert = "?error=You must be logged in to view this page.";
        }

        request.getRequestDispatcher("index.jsp" + alert).forward(request, response);
    }
}
