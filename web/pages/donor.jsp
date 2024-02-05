<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String role = (String) session.getAttribute("role"); %>

<div class="card border-0 shadow mb-3">
    <div class="card-body d-flex justify-content-between align-items-center">
        <p class="m-0 fs-4 fw-semibold">Donor</p>

        <button type="button" class="btn btn-sm btn-primary shadow-0 text-capitalize ms-2" data-bs-toggle="modal" data-bs-target="#upsertDonorModal" onclick="upsertInit()">
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
                        <th scope="col">IC Number</th>
                        <th scope="col">Fullname</th>
                        <th scope="col">Date of Birth</th>
                        <th scope="col">Gender</th>
                        <th scope="col">Blood Type</th>
                        <th scope="col">Total Eligibility</th>
                        <th scope="col">Total Donation</th>
                        <th scope="col" class="text-center">Action</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="donor" items="${donorList}" varStatus="num">
                        <tr>
                            <th scope="row">${num.index + 1}</th>
                            <td>${donor.getId()}</td>
                            <td>${donor.getIcNumber()}</td>
                            <td>${donor.getFullname()}</td>
                            <td>${donor.getDob()}</td>
                            <td>${donor.getGender()}</td>
                            <td>${donor.getBloodType()}</td>
                            <td>${donor.getTotalEligibility()}</td>
                            <td>${donor.getTotalDonation()}</td>
                            <td class="text-center">
                                <button type="button" class="btn btn-sm btn-link text-capitalize p-0" data-bs-toggle="modal" data-bs-target="#upsertDonorModal" onclick="upsertInit('${donor.getId()}')">Edit</button>
                                <% if (role != null && role.equals("admin")) { %>
                                <button type="button" class="btn btn-sm btn-link text-capitalize p-0" data-bs-toggle="modal" data-bs-target="#deleteDonorModal" onclick="deleteInit('${donor.getId()}')">
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

<div class="modal fade" id="upsertDonorModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="upsertDonorModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <form id="upsertDonorForm" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="upsertDonorModalLabel"></h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <!--
                    IC Number
                    Fullname
                    Date of Birth
                    Gender
                    Weight
                    Height
                    Blood Type
                    -->

                    <!-- Integer.parseInt(request.getParameter("referrer_donor_id")),
                    request.getParameter("ic_number"),
                    request.getParameter("fullname"),
                    request.getParameter("dob"),
                    request.getParameter("gender"),
                    Integer.parseInt(request.getParameter("weight")),
                    Integer.parseInt(request.getParameter("height")),
                    request.getParameter("blood_type") -->

                    <div class="row">
                        <div class="col-12">
                            <div class="mb-3">
                                <label for="referrer_donor_id" class="form-label">Referrer Donor</label>
                                <select class="form-select" id="referrer_donor_id" name="referrer_donor_id"></select>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="ic_number" class="form-label">IC Number</label>
                                <input type="text" class="form-control" id="ic_number" name="ic_number" placeholder="e.g. 123456-12-1234" required>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="fullname" class="form-label">Fullname</label>
                                <input type="text" class="form-control" id="fullname" name="fullname" placeholder="e.g. John Doe" required>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="dob" class="form-label">Date of Birth</label>
                                <input type="date" class="form-control" id="dob" name="dob" required>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="gender" class="form-label">Gender</label>

                                <div class="d-flex">
                                    <div class="form-check me-3">
                                        <input class="form-check-input" type="radio" name="gender" id="genderMale" value="Male" required>
                                        <label class="form-check-label" for="genderMale">Male</label>
                                    </div>

                                    <div class="form-check me-3">
                                        <input class="form-check-input" type="radio" name="gender" id="genderFemale" value="Female">
                                        <label class="form-check-label" for="genderFemale">Female</label>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="weight" class="form-label">Weight (kg)</label>
                                <input type="number" class="form-control" id="weight" name="weight" min="0" placeholder="e.g. 70" required>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="height" class="form-label">Height (cm)</label>
                                <input type="number" class="form-control" id="height" name="height" min="0" placeholder="e.g. 170" required>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="blood_type" class="form-label">Blood Type</label>
                                <select class="form-select" id="blood_type" name="blood_type" required>
                                    <option value="" selected disabled hidden>Select Blood Type</option>
                                    <option value="AB+">AB+</option>
                                    <option value="AB-">AB-</option>
                                    <option value="A+">A+</option>
                                    <option value="A-">A-</option>
                                    <option value="B+">B+</option>
                                    <option value="B-">B-</option>
                                    <option value="O+">O+</option>
                                    <option value="O-">O-</option>
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
<div class="modal fade" id="deleteDonorModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="deleteDonorModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="deleteDonorModalLabel">Delete Donor</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body text-center my-3">
                <i class="bi bi-exclamation-triangle-fill text-warning fs-1 mb-3"></i>
                <p class="fs-4 fw-semibold">Donor ID: #<span id="donorID"></span></p>
                <p class="mb-3">
                    Are you sure you want to delete this donor?
                    <br>
                    This action cannot be undone.
                </p>
            </div>

            <div class="modal-footer">
                <a id="deleteDonorBtn" href="#" class="btn btn-sm btn-danger shadow-0 text-capitalize">
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

    function upsertInit(id = null) {
        if (id) {
            document.getElementById('upsertDonorModalLabel').innerHTML = 'Edit Donor';
            document.getElementById('upsertDonorForm').action = 'donor/' + id;
            getDonorById(id);
        } else {
            document.getElementById('upsertDonorModalLabel').innerHTML = 'Create New Donor';
            document.getElementById('upsertDonorForm').action = 'donor';

            // Empty value
            document.getElementById('referrer_donor_id').value = '';
            document.getElementById('ic_number').value = '';
            document.getElementById('fullname').value = '';
            document.getElementById('dob').value = '';
            document.getElementById('genderMale').checked = true;
            document.getElementById('weight').value = '';
            document.getElementById('height').value = '';
            document.getElementById('blood_type').value = '';
        }
    }

    function deleteInit(id) {
        document.getElementById('donorID').innerHTML = id;
        document.getElementById('deleteDonorBtn').href = 'donor/' + id + '/delete';
    }

    function getDonorById(id) {
        fetch('donor/' + id).then(response => response.json()).then(data => {
            // Set value
            document.getElementById('referrer_donor_id').value = data.referrer_donor_id;
            document.getElementById('ic_number').value = data.ic_number;
            document.getElementById('fullname').value = data.fullname;
            document.getElementById('dob').value = data.dob;
            document.getElementById('gender' + data.gender).checked = true;
            document.getElementById('weight').value = data.weight;
            document.getElementById('height').value = data.height;
            document.getElementById('blood_type').value = data.blood_type;
        });
    }

    function getDonorList() {
        document.getElementById('referrer_donor_id').innerHTML = '<option value="" selected>-</option>';
        fetch('donor/list').then(response => response.json()).then(data => {
            data.forEach(donor => {
                document.getElementById('referrer_donor_id').innerHTML += "<option value='" + donor.id + "'>" + donor.id + " - " + donor.fullname + "</option>";
            });
        });
    }
</script>