<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String role = (String) session.getAttribute("role"); %>

<div class="card border-0 shadow mb-3">
    <div class="card-body d-flex justify-content-between align-items-center">
        <p class="m-0 fs-4 fw-semibold">User</p>

        <button type="button" class="btn btn-sm btn-primary shadow-0 text-capitalize ms-2" data-bs-toggle="modal" data-bs-target="#upsertUserModal" onclick="upsertInit()">
            <i class="bi bi-plus-lg me-2"></i>Create
        </button>
    </div>
</div>

<div class="card border-0 shadow">
    <div class="card-body">
        <div class="table-responsive text-nowrap">
            <table id="datatable" class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">System ID</th>
                        <th scope="col">Firstname</th>
                        <th scope="col">Lastname</th>
                        <th scope="col">Username</th>
                        <th scope="col">Role</th>
                        <th scope="col" class="text-center">Action</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="user" items="${userList}" varStatus="num">
                        <tr>
                            <th scope="row">${num.index + 1}</th>
                            <td>${user.getId()}</td>
                            <td>${user.getFirstname()}</td>
                            <td>${user.getLastname()}</td>
                            <td>${user.getUsername()}</td>
                            <td>${user.getRole()}</td>
                            <td class="text-center">
                                <button type="button" class="btn btn-sm btn-link text-capitalize p-0 mx-1" data-bs-toggle="modal" data-bs-target="#upsertUserModal" onclick="upsertInit('${user.getId()}')">Edit</button>
                                <% if (role != null && role.equals("admin")) { %>
                                <button type="button" class="btn btn-sm btn-link text-capitalize p-0 mx-1" data-bs-toggle="modal" data-bs-target="#deleteUserModal" onclick="deleteInit('${user.getId()}')">
                                    Delete
                                </button>
                                <% } %>

                                <!-- Test Delete -->
                                <!-- <a href="users/${user.getId()}/delete" class="btn btn-sm btn-link text-capitalize p-0 mx-1">Delete</a> -->
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="upsertUserModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="upsertUserModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <form action="users" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="upsertUserModalLabel"></h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <!--
                    Role (Dropdown)
                    Firstname
                    Lastname
                    Username
                    Password
                    -->

                    <!-- request.getParameter("role"),
                    request.getParameter("firstname"),
                    request.getParameter("lastname"),
                    request.getParameter("username"),
                    request.getParameter("password") -->

                    <div class="row">
                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="role" class="form-label">Role<span class="text-danger">*</span></label>
                                <select class="form-select" id="role" name="role" required>
                                    <option selected disabled>Select Role</option>
                                    <option value="admin">Admin</option>
                                    <option value="staff">Staff</option>
                                </select>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="firstname" class="form-label">Firstname<span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="firstname" name="firstname" placeholder="e.g. John" required>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="lastname" class="form-label">Lastname<span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="lastname" name="lastname" placeholder="e.g. Doe" required>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="username" class="form-label">Username<span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="e.g. johndoe" required>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="password" class="form-label">Password<span class="text-danger">*</span></label>
                                <input type="text" class="form-control" id="password" name="password" placeholder="e.g. admin@12345" required>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="submit" class="btn btn-sm btn-primary shadow-0 text-capitalize">
                        <i class="bi bi-upload me-2"></i>
                        Submit
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>

<% if (role != null && role.equals("admin")) { %>
<div class="modal fade" id="deleteUserModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="deleteUserModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="deleteUserModalLabel">Delete User</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body text-center my-3">
                <i class="bi bi-exclamation-triangle-fill text-warning fs-1 mb-3"></i>
                <p class="fs-4 fw-semibold">User ID: #<span id="userID"></span></p>
                <p class="mb-3">
                    Are you sure you want to delete this user?
                    <br>
                    This action cannot be undone.
                </p>
            </div>

            <div class="modal-footer">
                <a id="deleteUserBtn" href="#" class="btn btn-sm btn-danger shadow-0 text-capitalize">
                    <i class="bi bi-trash me-2"></i>
                    Delete
                </a>
            </div>
        </div>
    </div>
</div>
<% } %>

<script>
    function upsertInit(id = null) {
        if (id) {
            document.getElementById('upsertUserModalLabel').innerHTML = 'Edit User';
        } else {
            document.getElementById('upsertUserModalLabel').innerHTML = 'Create New User';
        }
    }

    function deleteInit(id) {
        document.getElementById('userID').innerHTML = id;
        document.getElementById('deleteUserBtn').href = 'users/' + id + '/delete';
    }
</script>