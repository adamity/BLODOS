package donationtype;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DonationTypeServlet", urlPatterns = {"/donation-type/*"})
public class DonationTypeServlet extends HttpServlet {
    private DonationTypeDAO donationTypeDAO = new DonationTypeDAO();

    /**
     * Endpoints Structure:
     * 
     * GET /donation-type
     * - Get all donation type
     * - Redirect to index.jsp
     * 
     * GET /donation-type/{id}
     * - Get donation type by ID
     * - Return JSON
     * 
     * GET /donation-type/list
     * - Get all donation type
     * - Return JSON
     * 
     * GET /donation-type/{id}/delete
     * - Delete donation type by ID
     * - Redirect to GET /donation-type
     * 
     * POST /donation-type/
     * - Add donation type
     * - Redirect to GET /donation-type
     * 
     * POST /donation-type/{id}
     * - Update donation type by ID
     * - Redirect to GET /donation-type
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        String alert = "";

        if (action == null || action.equals("/")) {
            // Get all donation type
            if (request.getSession() != null && request.getSession().getAttribute("token") != null) {
                request.getSession().setAttribute("action", "donation-type");
                request.getSession().setAttribute("title", "Donation Type");

                List<DonationType> donationTypeList = donationTypeDAO.getAll();
                request.setAttribute("donationTypeList", donationTypeList);
            } else {
                alert = "?error=You must be logged in to view this page.";
            }

            request.getRequestDispatcher("index.jsp" + alert).forward(request, response);
        } else {
            // This section will be requested by AJAX, so we don't need to forward to a JSP page but instead return JSON
            String[] pathParts = action.split("/");

            if (pathParts.length == 2 && pathParts[1].equals("list")) {
                List<DonationType> donationTypeList = donationTypeDAO.getAll();
                response.setContentType("application/json");
                response.getWriter().print(DonationType.toJSONArray(donationTypeList));
            } else if (pathParts.length == 2) {
                // Get donation type by ID
                DonationType donationType = donationTypeDAO.getById(Integer.parseInt(pathParts[1]));
                response.setContentType("application/json");
                response.getWriter().print(donationType.toJSON());
            } else if (pathParts.length == 3 && pathParts[2].equals("delete")) {
                // Delete donation type by ID
                donationTypeDAO.delete(Integer.parseInt(pathParts[1]));
                response.sendRedirect(request.getContextPath() + "/donation-type");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            // Add donation type
            DonationType donationType = new DonationType(
                request.getParameter("type_name")
            );

            donationTypeDAO.add(donationType);
            response.sendRedirect(request.getContextPath() + "/donation-type");
        } else {
            String[] pathParts = action.split("/");
            int donationId = Integer.parseInt(pathParts[1]);

            if (pathParts.length == 2) {
                // Update donation type by ID
                DonationType donationType = new DonationType(
                    donationId,
                    request.getParameter("type_name")
                );

                donationTypeDAO.update(donationType);
                response.sendRedirect(request.getContextPath() + "/donation-type");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Donation Type Servlet: handles CRUD operations for donation type";
    }
}
