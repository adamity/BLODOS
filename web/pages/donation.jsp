<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String role = (String) session.getAttribute("role"); %>

<div class="card border-0 shadow mb-3">
    <div class="card-body d-flex justify-content-between align-items-center">
        <p class="m-0 fs-4 fw-semibold">Donation</p>

        <button type="button" class="btn btn-sm btn-primary shadow-0 text-capitalize ms-2" data-bs-toggle="modal" data-bs-target="#upsertDonationModal" onclick="upsertInit()">
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
                        <th scope="col">Fullname</th>
                        <th scope="col">Date</th>
                        <th scope="col">Time</th>
                        <th scope="col">Quantity (ml)</th>
                        <th scope="col">Status</th>
                        <th scope="col" class="text-center">Action</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="donation" items="${donationList}" varStatus="num">
                        <tr>
                            <th scope="row">${num.index + 1}</th>
                            <td>${donation.getId()}</td>
                            <td>${donation.getDonor().getFullname()}</td>
                            <td>${donation.getDateFormatted()}</td>
                            <td>${donation.getTimeFormatted()}</td>
                            <td>${donation.getQuantity()}</td>
                            <td>${donation.getStatus()}</td>
                            <td class="text-center">
                                <button type="button" class="btn btn-sm btn-link text-capitalize p-0" data-bs-toggle="modal" data-bs-target="#upsertDonationModal" onclick="upsertInit('${donation.getId()}')">Edit</button>
                                <% if (role != null && role.equals("admin")) { %>
                                <button type="button" class="btn btn-sm btn-link text-capitalize p-0" data-bs-toggle="modal" data-bs-target="#deleteDonationModal" onclick="deleteInit('${donation.getId()}')">
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

<div class="modal fade" id="upsertDonationModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="upsertDonationModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <form id="upsertDonationForm" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="upsertDonationModalLabel"></h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <!--
                    Donor ID (Donor) (Search)
                    User ID (User) (Handle on backend)
                    Date
                    Time
                    Quantity (ml)
                    Status (Dropdown)
                    -->

                    <!-- Integer.parseInt(request.getParameter("donor_id")),
                    Integer.parseInt(request.getParameter("user_id")),
                    request.getParameter("date"),
                    request.getParameter("time"),
                    Integer.parseInt(request.getParameter("quantity")),
                    request.getParameter("status") -->

                    <div class="row">
                        <div class="col-12">
                            <div class="mb-3">
                                <label for="donor_id" class="form-label">Donor<span class="text-danger">*</span></label>
                                <select class="form-select" id="donor_id" name="donor_id" required></select>
                            </div>
                        </div>

                        <div class="col-12">
                            <div class="mb-3">
                                <label class="form-label">Donation Type<span class="text-danger">*</span></label>
                                <div class="card shadow-0">
                                    <div class="card-body">
                                        <div class="row" id="donationTypeList"></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="date" class="form-label">Date<span class="text-danger">*</span></label>
                                <input type="date" class="form-control" id="date" name="date" required>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="time" class="form-label">Time<span class="text-danger">*</span></label>
                                <input type="time" class="form-control" id="time" name="time" required>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="quantity" class="form-label">Quantity (ml)<span class="text-danger">*</span></label>
                                <input type="number" class="form-control" id="quantity" name="quantity" placeholder="e.g. 470" required>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="status" class="form-label">Status<span class="text-danger">*</span></label>
                                <select class="form-select" id="status" name="status" required>
                                    <option value="" selected disabled hidden>Select Status</option>
                                    <option value="Pending">Pending</option>
                                    <option value="Confirmed">Confirmed</option>
                                    <option value="Completed">Completed</option>
                                    <option value="Cancelled">Cancelled</option>
                                </select>
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
<div class="modal fade" id="deleteDonationModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="deleteDonationModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="deleteDonationModalLabel">Delete Donation</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body text-center my-3">
                <i class="bi bi-exclamation-triangle-fill text-warning fs-1 mb-3"></i>
                <p class="fs-4 fw-semibold">Donation ID: #<span id="donationID"></span></p>
                <p class="mb-3">
                    Are you sure you want to delete this donation?
                    <br>
                    This action cannot be undone.
                </p>
            </div>

            <div class="modal-footer">
                <a id="deleteDonationBtn" href="#" class="btn btn-sm btn-danger shadow-0 text-capitalize">
                    <i class="bi bi-trash me-2"></i>
                    Delete
                </a>
            </div>
        </div>
    </div>
</div>
<% } %>

<script>
    getDonorList();
    getDonationTypeList();

    function upsertInit(id = null) {
        if (id) {
            document.getElementById('upsertDonationModalLabel').innerHTML = 'Edit Donation';
            document.getElementById('upsertDonationForm').action = 'donation/' + id;
            getDonationById(id);
        } else {
            document.getElementById('upsertDonationModalLabel').innerHTML = 'Create New Donation';
            document.getElementById('upsertDonationForm').action = 'donation';

            // Empty value
            document.getElementById('donor_id').value = '';
            document.getElementById('date').value = '';
            document.getElementById('time').value = '';
            document.getElementById('quantity').value = '';
            document.getElementById('status').value = '';
        }
    }

    function deleteInit(id) {
        document.getElementById('donationID').innerHTML = id;
        document.getElementById('deleteDonationBtn').href = 'donation/' + id + '/delete';
    }

    function getDonationById(id) {
        fetch('donation/' + id).then(response => response.json()).then(data => {
            // Set value
            document.getElementById('donor_id').value = data.donor_id;
            document.getElementById('date').value = data.date;
            document.getElementById('time').value = data.time.substring(0, 5);
            document.getElementById('quantity').value = data.quantity;
            document.getElementById('status').value = data.status;

            // Set donation type
            data.donation_type_list.forEach(donationType => {
                document.getElementById('donation_type_' + donationType.id).checked = true;
            });
        });
    }

    function getDonorList() {
        document.getElementById('donor_id').innerHTML = '<option value="" selected disabled hidden>Select Donor</option>';
        fetch('donor/list').then(response => response.json()).then(data => {
            data.forEach(donor => {
                document.getElementById('donor_id').innerHTML += "<option value='" + donor.id + "'>" + donor.id + " - " + donor.fullname + "</option>";
            });
        });
    }

    function getDonationTypeList() {
        document.getElementById('donationTypeList').innerHTML = '';
        fetch('donation-type/list').then(response => response.json()).then(data => {
            data.forEach(donationType => {
                document.getElementById('donationTypeList').innerHTML += "<div class='col-12 col-lg-6'><div class='form-check'><input class='form-check-input' type='checkbox' value='" + donationType.id + "' id='donation_type_" + donationType.id + "' name='donation_type_ids'><label class='form-check-label' for='donation_type_" + donationType.id + "'>" + donationType.type_name;
            });
        });
    }
</script>