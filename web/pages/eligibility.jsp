<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String role = (String) session.getAttribute("role"); %>

<div class="card border-0 shadow mb-3">
    <div class="card-body d-flex justify-content-between align-items-center">
        <p class="m-0 fs-4 fw-semibold">Eligibility</p>

        <button type="button" class="btn btn-sm btn-primary shadow-0 text-capitalize ms-2" data-bs-toggle="modal" data-bs-target="#upsertEligibilityModal" onclick="upsertInit()">
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
                        <th scope="col">Sleep Hours</th>
                        <th scope="col">Meal Before Donation</th>
                        <th scope="col">Medical Illness</th>
                        <th scope="col">High Risk Activity</th>
                        <th scope="col">Status</th>
                        <th scope="col" class="text-center">Action</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="eligibility" items="${eligibilityList}" varStatus="num">
                        <tr>
                            <th scope="row">${num.index + 1}</th>
                            <td>${eligibility.getId()}</td>
                            <td>${eligibility.getDonor().getFullname()}</td>
                            <td>${eligibility.getSleepHours()}</td>
                            <td>${eligibility.getMealBeforeDonation()}</td>
                            <td>${eligibility.getMedicalIllness()}</td>
                            <td>${eligibility.getHighRiskActivity()}</td>
                            <td>${eligibility.getStatus()}</td>
                            <td class="text-center">
                                <button type="button" class="btn btn-sm btn-link text-capitalize p-0" data-bs-toggle="modal" data-bs-target="#upsertEligibilityModal" onclick="upsertInit('${eligibility.getId()}')">Edit</button>
                                <% if (role != null && role.equals("admin")) { %>
                                <button type="button" class="btn btn-sm btn-link text-capitalize p-0" data-bs-toggle="modal" data-bs-target="#deleteEligibilityModal" onclick="deleteInit('${eligibility.getId()}')">
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

<div class="modal fade" id="upsertEligibilityModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="upsertEligibilityModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <form id="upsertEligibilityForm" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="upsertEligibilityModalLabel"></h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <!--
                    Donor ID (Donor) (Search, Dropdown)
                    Sleep Hours
                    Meal Before Donation
                    Medical Illness
                    High Risk Activity
                    Status (Calculated On Backend)
                    -->

                    <!-- Integer.parseInt(request.getParameter("donor_id")),
                    Integer.parseInt(request.getParameter("sleep_hours")),
                    Integer.parseInt(request.getParameter("meal_before_donation")),
                    Integer.parseInt(request.getParameter("medical_illness")),
                    Integer.parseInt(request.getParameter("high_risk_activity")) -->

                    <div class="row">
                        <div class="col-12">
                            <div class="mb-3">
                                <label for="donor_id" class="form-label">Donor<span class="text-danger">*</span></label>
                                <select class="form-select" id="donor_id" name="donor_id" required></select>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="sleep_hours" class="form-label">Sleep Hours<span class="text-danger">*</span></label>
                                <input type="number" class="form-control" id="sleep_hours" name="sleep_hours" placeholder="e.g. 8" required>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="meal_before_donation" class="form-label">Meal Before Donation<span class="text-danger">*</span></label>
                                <select class="form-select" id="meal_before_donation" name="meal_before_donation" required>
                                    <option value="" selected disabled hidden>Select Meal Before Donation</option>
                                    <option value="1">Yes</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="medical_illness" class="form-label">Medical Illness<span class="text-danger">*</span></label>
                                <select class="form-select" id="medical_illness" name="medical_illness" required>
                                    <option value="" selected disabled hidden>Select Medical Illness</option>
                                    <option value="1">Yes</option>
                                    <option value="0">No</option>
                                </select>
                            </div>
                        </div>

                        <div class="col-12 col-lg-6">
                            <div class="mb-3">
                                <label for="high_risk_activity" class="form-label">High Risk Activity<span class="text-danger">*</span></label>
                                <select class="form-select" id="high_risk_activity" name="high_risk_activity" required>
                                    <option value="" selected disabled hidden>Select High Risk Activity</option>
                                    <option value="1">Yes</option>
                                    <option value="0">No</option>
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
<div class="modal fade" id="deleteEligibilityModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="deleteEligibilityModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="deleteEligibilityModalLabel">Delete Eligibility</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body text-center my-3">
                <i class="bi bi-exclamation-triangle-fill text-warning fs-1 mb-3"></i>
                <p class="fs-4 fw-semibold">Eligibility ID: #<span id="eligibilityID"></span></p>
                <p class="mb-3">
                    Are you sure you want to delete this eligibility?
                    <br>
                    This action cannot be undone.
                </p>
            </div>

            <div class="modal-footer">
                <a id="deleteEligibilityBtn" href="#" class="btn btn-sm btn-danger shadow-0 text-capitalize">
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
            document.getElementById('upsertEligibilityModalLabel').innerHTML = 'Edit Eligibility';
            document.getElementById('upsertEligibilityForm').action = 'eligibility/' + id;
            getEligibilityById(id);
        } else {
            document.getElementById('upsertEligibilityModalLabel').innerHTML = 'Create New Eligibility';
            document.getElementById('upsertEligibilityForm').action = 'eligibility';

            // Empty value
            document.getElementById('donor_id').value = '';
            document.getElementById('sleep_hours').value = '';
            document.getElementById('meal_before_donation').value = '';
            document.getElementById('medical_illness').value = '';
            document.getElementById('high_risk_activity').value = '';
        }
    }

    function deleteInit(id) {
        document.getElementById('eligibilityID').innerHTML = id;
        document.getElementById('deleteEligibilityBtn').href = 'eligibility/' + id + '/delete';
    }

    function getEligibilityById(id) {
        fetch('eligibility/' + id).then(response => response.json()).then(data => {
            // Set value
            document.getElementById('donor_id').value = data.donor_id;
            document.getElementById('sleep_hours').value = data.sleep_hours;
            document.getElementById('meal_before_donation').value = data.meal_before_donation;
            document.getElementById('medical_illness').value = data.medical_illness;
            document.getElementById('high_risk_activity').value = data.high_risk_activity;
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
</script>