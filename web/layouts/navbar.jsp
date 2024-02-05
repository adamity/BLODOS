<!-- Get token session -->
<%
    String token = (String) session.getAttribute("token");
    String role = (String) session.getAttribute("role");
%>

<nav class="navbar sticky-top bg-light-subtle">
    <div class="container">
        <div class="d-flex justify-content-between align-items-center">
            <a class="navbar-brand fw-bold text-seance">BLODOS</a>
        </div>

        <!--
            Only show is token is not null
            Token is set in LoginServlet.java
        -->
        <% if (token != null) { %>
        <div>
            <a href="RouteServlet?action=dashboard" class="btn btn-link text-dark text-decoration-none text-capitalize px-3 border-0 rounded-0">
                <i class="bi bi-house me-2"></i>Dashboard
            </a>

            <a href="donor" class="btn btn-link text-dark text-decoration-none text-capitalize px-3 border-0 rounded-0">
                <i class="bi bi-people me-2"></i>Donor
            </a>

            <a href="eligibility" class="btn btn-link text-dark text-decoration-none text-capitalize px-3 border-0 rounded-0">
                <i class="bi bi-person-check me-2"></i>Eligibility
            </a>

            <a href="donation" class="btn btn-link text-dark text-decoration-none text-capitalize px-3 border-0 rounded-0">
                <i class="bi bi-journal-medical me-2"></i>Donation
            </a>

            <!-- 
                Only show if role is admin
                Role is set in LoginServlet.java
            -->
            <% if (role != null && role.equals("admin")) { %>
            <div class="dropdown d-inline-block">
                <button class="btn btn-link text-dark text-decoration-none text-capitalize px-3 border-start rounded-0 dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                    <i class="bi bi-gear me-2"></i>Admin
                </button>

                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                    <li><a class="dropdown-item" href="donation-type">Donation Type</a></li>
                    <li><a class="dropdown-item" href="users">User</a></li>
                </ul>
            </div>
            <% } %>

            <a href="LogoutServlet" class="btn btn-link text-danger text-decoration-none text-capitalize px-3 border-start rounded-0">
                <i class="bi bi-box-arrow-right me-2"></i>Logout
            </a>
        </div>
        <% } %>
    </div>
</nav>