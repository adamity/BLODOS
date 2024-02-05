package donor;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DonorServlet", urlPatterns = {"/donor/*"})
public class DonorServlet extends HttpServlet {
    private DonorDAO donorDAO = new DonorDAO();

    /**
     * Endpoints Structure:
     * 
     * GET /donor
     * - Get all donor
     * - Redirect to index.jsp
     * 
     * GET /donor/{id}
     * - Get donor by ID
     * - Return JSON
     * 
     * GET /donor/list
     * - Get all donor
     * - Return JSON
     * 
     * GET /donor/{id}/delete
     * - Delete donor by ID
     * - Redirect to GET /donor
     * 
     * POST /donor/
     * - Add donor
     * - Redirect to GET /donor
     * 
     * POST /donor/{id}
     * - Update donor by ID
     * - Redirect to GET /donor
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        String alert = "";

        if (action == null || action.equals("/")) {
            // Get all donor
            if (request.getSession() != null && request.getSession().getAttribute("token") != null) {
                request.getSession().setAttribute("action", "donor");
                request.getSession().setAttribute("title", "Donor");

                List<Donor> donorList = donorDAO.getAll();
                request.setAttribute("donorList", donorList);
            } else {
                alert = "?error=You must be logged in to view this page.";
            }

            request.getRequestDispatcher("index.jsp" + alert).forward(request, response);
        } else {
            // This section will be requested by AJAX, so we don't need to forward to a JSP page but instead return JSON
            String[] pathParts = action.split("/");

            if (pathParts.length == 2 && pathParts[1].equals("list")) {
                List<Donor> donorList = donorDAO.getAll();
                response.setContentType("application/json");
                response.getWriter().print(Donor.toJSONArray(donorList));
            } else if (pathParts.length == 2) {
                // Get donor by ID
                Donor donor = donorDAO.getById(Integer.parseInt(pathParts[1]));
                response.setContentType("application/json");
                response.getWriter().print(donor.toJSON());
            } else if (pathParts.length == 3 && pathParts[2].equals("delete")) {
                // Delete donor by ID
                donorDAO.delete(Integer.parseInt(pathParts[1]));
                response.sendRedirect(request.getContextPath() + "/donor");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            // Add donor
            Donor donor = new Donor(
                Integer.parseInt(request.getParameter("user_id")),
                Integer.parseInt(request.getParameter("referrer_donor_id")),
                request.getParameter("ic_number"),
                request.getParameter("fullname"),
                request.getParameter("dob"),
                request.getParameter("gender"),
                Integer.parseInt(request.getParameter("weight")),
                Integer.parseInt(request.getParameter("height")),
                request.getParameter("blood_type")
            );

            donorDAO.add(donor);
            response.sendRedirect(request.getContextPath() + "/donor");
        } else {
            String[] pathParts = action.split("/");
            int donorId = Integer.parseInt(pathParts[1]);

            if (pathParts.length == 2) {
                // Update donor by ID
                Donor donor = new Donor(
                    donorId,
                    Integer.parseInt(request.getParameter("user_id")),
                    Integer.parseInt(request.getParameter("referrer_donor_id")),
                    request.getParameter("ic_number"),
                    request.getParameter("fullname"),
                    request.getParameter("dob"),
                    request.getParameter("gender"),
                    Integer.parseInt(request.getParameter("weight")),
                    Integer.parseInt(request.getParameter("height")),
                    request.getParameter("blood_type")
                );

                donorDAO.update(donor);
                response.sendRedirect(request.getContextPath() + "/donor");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Donor Servlet: handles CRUD operations for donor";
    }
}
