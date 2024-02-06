package donation;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import donationdonationtype.*;
import utility.*;

@WebServlet(name = "DonationServlet", urlPatterns = {"/donation/*"})
public class DonationServlet extends HttpServlet {
    private DonationDAO donationDAO = new DonationDAO();

    /**
     * Endpoints Structure:
     * 
     * GET /donation
     * - Get all donation
     * - Redirect to index.jsp
     * 
     * GET /donation/{id}
     * - Get donation by ID
     * - Return JSON
     * 
     * GET /donation/{id}/delete
     * - Delete donation by ID
     * - Redirect to GET /donation
     * 
     * POST /donation/
     * - Add donation
     * - Redirect to GET /donation
     * 
     * POST /donation/{id}
     * - Update donation by ID
     * - Redirect to GET /donation
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        String alert = "";

        if (action == null || action.equals("/")) {
            // Get all donation
            if (request.getSession() != null && request.getSession().getAttribute("token") != null) {
                request.getSession().setAttribute("action", "donation");
                request.getSession().setAttribute("title", "Donation");

                List<Donation> donationList = donationDAO.getAll();
                request.setAttribute("donationList", donationList);
            } else {
                alert = "?error=You must be logged in to view this page.";
            }

            request.getRequestDispatcher("index.jsp" + alert).forward(request, response);
        } else {
            // This section will be requested by AJAX, so we don't need to forward to a JSP page but instead return JSON
            String[] pathParts = action.split("/");
            int donationId = Integer.parseInt(pathParts[1]);

            if (pathParts.length == 2) {
                // Get donation by ID
                Donation donation = donationDAO.getById(donationId);
                response.setContentType("application/json");
                response.getWriter().print(donation.toJSON());
            } else if (pathParts.length == 3 && pathParts[2].equals("delete")) {
                // Delete donation by ID
                donationDAO.delete(donationId);
                response.sendRedirect(request.getContextPath() + "/donation");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getPathInfo();
            String token = (String) request.getSession().getAttribute("token");
            int userId = Integer.parseInt(Encryptor.decrypt(token));

            if (action == null || action.equals("/")) {
                // Add donation
                Donation donation = new Donation(
                    Integer.parseInt(request.getParameter("donor_id")),
                    userId,
                    request.getParameter("date"),
                    request.getParameter("time"),
                    Integer.parseInt(request.getParameter("quantity")),
                    request.getParameter("status")
                );

                String insertId = donationDAO.add(donation);
                String[] donationTypeIds = request.getParameterValues("donation_type_ids");

                if (donationTypeIds != null) {
                    for (String donationTypeId : donationTypeIds) {
                        DonationDonationType donationTypeDonation = new DonationDonationType(
                            Integer.parseInt(insertId),
                            Integer.parseInt(donationTypeId)
                        );

                        DonationDonationTypeDAO donationTypeDonationDAO = new DonationDonationTypeDAO();
                        donationTypeDonationDAO.add(donationTypeDonation);
                    }
                }

                response.sendRedirect(request.getContextPath() + "/donation");
            } else {
                // Update donation by ID
                String[] pathParts = action.split("/");
                int donationId = Integer.parseInt(pathParts[1]);

                if (pathParts.length == 2) {
                    // Update donation by ID
                    Donation donation = new Donation(
                        donationId,
                        Integer.parseInt(request.getParameter("donor_id")),
                        userId,
                        request.getParameter("date"),
                        request.getParameter("time"),
                        Integer.parseInt(request.getParameter("quantity")),
                        request.getParameter("status")
                    );

                    donationDAO.update(donation);
                    response.sendRedirect(request.getContextPath() + "/donation");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Donation Servlet: handles CRUD operations for donation";
    }
}
