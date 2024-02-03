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
                        <th scope="col">Action</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>100</td>
                        <td>Whole Blood</td>
                        <td>2</td>
                        <td>
                            <button type="button" class="btn btn-sm btn-link text-capitalize p-0" data-bs-toggle="modal" data-bs-target="#upsertDonationTypeModal" onclick="upsertInit('100')">Edit</button>
                            <% if (role != null && role.equals("admin")) { %>
                            <button type="button" class="btn btn-sm btn-link text-capitalize p-0" data-bs-toggle="modal" data-bs-target="#deleteDonationTypeModal" onclick="deleteInit('100')">
                                Delete
                            </button>
                            <% } %>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="upsertDonationTypeModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="upsertDonationTypeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="upsertDonationTypeModalLabel"></h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="e.g. Whole Blood" required>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary shadow-0 text-capitalize">
                    <i class="bi bi-upload me-2"></i>
                    Submit
                </button>
            </div>
        </div>
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
                <button type="button" class="btn btn-sm btn-danger shadow-0 text-capitalize" data-bs-dismiss="modal">
                    <i class="bi bi-trash me-2"></i>
                    Delete
                </button>
            </div>
        </div>
    </div>
</div>
<% } %>

<script>
    function upsertInit(id = null) {
        if (id) {
            document.getElementById('upsertDonationTypeModalLabel').innerHTML = 'Edit Donation Type';
        } else {
            document.getElementById('upsertDonationTypeModalLabel').innerHTML = 'Create New Donation Type';
        }
    }

    function deleteInit(id) {
        document.getElementById('donationTypeID').innerHTML = id;
    }
</script>