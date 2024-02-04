package eligibility;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EligibilityServlet", urlPatterns = {"/eligibility/*"})
public class EligibilityServlet extends HttpServlet {
    private EligibilityDAO eligibilityDAO = new EligibilityDAO();

    /**
     * Endpoints Structure:
     * 
     * GET /eligibility
     * - Get all eligibility
     * - Redirect to index.jsp
     * 
     * GET /eligibility/{id}
     * - Get eligibility by ID
     * - Return JSON
     * 
     * GET /eligibility/{id}/delete
     * - Delete eligibility by ID
     * - Redirect to GET /eligibility
     * 
     * POST /eligibility/
     * - Add eligibility
     * - Redirect to GET /eligibility
     * 
     * POST /eligibility/{id}
     * - Update eligibility by ID
     * - Redirect to GET /eligibility
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        String alert = "";

        if (action == null || action.equals("/")) {
            // Get all eligibility
            if (request.getSession() != null && request.getSession().getAttribute("token") != null) {
                request.getSession().setAttribute("action", "eligibility");
                request.getSession().setAttribute("title", "Eligibility");

                List<Eligibility> eligibilityList = eligibilityDAO.getAll();
                request.setAttribute("eligibilityList", eligibilityList);
            } else {
                alert = "?error=You must be logged in to view this page.";
            }

            request.getRequestDispatcher("index.jsp" + alert).forward(request, response);
        } else {
            // This section will be requested by AJAX, so we don't need to forward to a JSP page but instead return JSON
            String[] pathParts = action.split("/");
            int eligibilityId = Integer.parseInt(pathParts[1]);

            if (pathParts.length == 2) {
                // Get eligibility by ID
                Eligibility eligibility = eligibilityDAO.getById(eligibilityId);
                response.setContentType("application/json");
                response.getWriter().print(eligibility.toJSON());
            } else if (pathParts.length == 3 && pathParts[2].equals("delete")) {
                // Delete eligibility by ID
                eligibilityDAO.delete(eligibilityId);
                response.sendRedirect(request.getContextPath() + "/eligibility");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            // Add eligibility
            Eligibility eligibility = new Eligibility(
                Integer.parseInt(request.getParameter("donor_id")),
                Integer.parseInt(request.getParameter("sleep_hours")),
                Integer.parseInt(request.getParameter("meal_before_donation")),
                Integer.parseInt(request.getParameter("medical_illness")),
                Integer.parseInt(request.getParameter("high_risk_activity"))
            );

            eligibilityDAO.add(eligibility);
            response.sendRedirect(request.getContextPath() + "/eligibility");
        } else {
            String[] pathParts = action.split("/");
            int eligibilityId = Integer.parseInt(pathParts[1]);

            if (pathParts.length == 2) {
                // Update eligibility by ID
                Eligibility eligibility = new Eligibility(
                    eligibilityId,
                    Integer.parseInt(request.getParameter("donor_id")),
                    Integer.parseInt(request.getParameter("sleep_hours")),
                    Integer.parseInt(request.getParameter("meal_before_donation")),
                    Integer.parseInt(request.getParameter("medical_illness")),
                    Integer.parseInt(request.getParameter("high_risk_activity"))
                );

                eligibilityDAO.update(eligibility);
                response.sendRedirect(request.getContextPath() + "/eligibility");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Eligibility Servlet: handles CRUD operations for eligibility";
    }
}
