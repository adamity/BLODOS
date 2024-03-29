<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String role = (String) session.getAttribute("role"); %>

<div class="card border-0 shadow mb-3">
    <div class="card-body d-flex justify-content-between align-items-center">
        <p class="m-0 fs-4 fw-semibold">Donation Type</p>

        <button type="button" class="btn btn-sm btn-primary shadow-0 text-capitalize ms-2" data-bs-toggle="modal" data-bs-target="#upsertDonationTypeModal" onclick="upsertInit()">
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
                        <th scope="col">Name</th>
                        <th scope="col">Total Donation</th>
                        <th scope="col" class="text-center">Action</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="donationType" items="${donationTypeList}" varStatus="num">
                        <tr>
                            <th scope="row">${num.index + 1}</th>
                            <td>${donationType.getId()}</td>
                            <td>${donationType.getTypeName()}</td>
                            <td>${donationType.getDonations().size()}</td>
                            <td class="text-center">
                                <button type="button" class="btn btn-sm btn-link text-capitalize p-0" data-bs-toggle="modal" data-bs-target="#upsertDonationTypeModal" onclick="upsertInit('${donationType.getId()}')">Edit</button>
                                <% if (role != null && role.equals("admin")) { %>
                                <button type="button" class="btn btn-sm btn-link text-capitalize p-0" data-bs-toggle="modal" data-bs-target="#deleteDonationTypeModal" onclick="deleteInit('${donationType.getId()}')">
                                    Delete
                                </button>
                                <% } %>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="upsertDonationTypeModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="upsertDonationTypeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form id="upsertDonationTypeForm" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="upsertDonationTypeModalLabel"></h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <!-- request.getParameter("type_name") -->

                    <label for="type_name" class="form-label">Name<span class="text-danger">*</span></label>
                    <input type="text" class="form-control" id="type_name" name="type_name" placeholder="e.g. Whole Blood" required>
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
<div class="modal fade" id="deleteDonationTypeModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="deleteDonationTypeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="deleteDonationTypeModalLabel">Delete Donation Type</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body text-center my-3">
                <i class="bi bi-exclamation-triangle-fill text-warning fs-1 mb-3"></i>
                <p class="fs-4 fw-semibold">Donation Type ID: #<span id="donationTypeID"></span></p>
                <p class="mb-3">
                    Are you sure you want to delete this donation type?
                    <br>
                    This action cannot be undone.
                </p>
            </div>

            <div class="modal-footer">
                <a id="deleteDonationTypeBtn" href="#" class="btn btn-sm btn-danger shadow-0 text-capitalize">
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
            document.getElementById('upsertDonationTypeModalLabel').innerHTML = 'Edit Donation Type';
            document.getElementById('upsertDonationTypeForm').action = 'donation-type/' + id;
            getDonationTypeById(id);
        } else {
            document.getElementById('upsertDonationTypeModalLabel').innerHTML = 'Create New Donation Type';
            document.getElementById('upsertDonationTypeForm').action = 'donation-type';

            // Empty value
            document.getElementById('type_name').value = '';
        }
    }

    function deleteInit(id) {
        document.getElementById('donationTypeID').innerHTML = id;
        document.getElementById('deleteDonationTypeBtn').href = 'donation-type/' + id + '/delete';
    }

    function getDonationTypeById(id) {
        fetch('donation-type/' + id).then(response => response.json()).then(data => {
            // Set value
            document.getElementById('type_name').value = data.type_name;
        });
    }
</script>