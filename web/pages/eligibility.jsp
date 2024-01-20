<div class="card border-0 shadow mb-3">
    <div class="card-body d-flex justify-content-between align-items-center">
        <p class="m-0 fs-4 fw-semibold">Eligibility</p>

        <button type="button" class="btn btn-sm btn-primary shadow-0 text-capitalize ms-2" data-bs-toggle="modal" data-bs-target="#createEligibilityModal">
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
                        <th scope="col">Action</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>100</td>
                        <td>John Doe</td>
                        <td>8</td>
                        <td>Yes</td>
                        <td>No</td>
                        <td>No</td>
                        <td>Eligible</td>
                        <td>
                            <a href="#">Edit</a>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>101</td>
                        <td>Jane Doe</td>
                        <td>7</td>
                        <td>No</td>
                        <td>Yes</td>
                        <td>Yes</td>
                        <td>Ineligible</td>
                        <td>
                            <a href="#">Edit</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="createEligibilityModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="createEligibilityModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="createEligibilityModalLabel">Create New Eligibility</h1>
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

                <div class="row">
                    <div class="col-12">
                        <div class="mb-3">
                            <label for="donorId" class="form-label">Donor</label>
                            <select class="form-select" id="donorId" name="donorId" required>
                                <option value="" selected disabled hidden>Select Donor</option>
                                <option value="100">100 - John Doe</option>
                                <option value="101">101 - Jane Doe</option>
                            </select>
                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="mb-3">
                            <label for="sleepHours" class="form-label">Sleep Hours</label>
                            <input type="number" class="form-control" id="sleepHours" name="sleepHours" placeholder="e.g. 8" required>
                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="mb-3">
                            <label for="mealBeforeDonation" class="form-label">Meal Before Donation</label>
                            <select class="form-select" id="mealBeforeDonation" name="mealBeforeDonation" required>
                                <option value="" selected disabled hidden>Select Meal Before Donation</option>
                                <option value="Yes">Yes</option>
                                <option value="No">No</option>
                            </select>
                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="mb-3">
                            <label for="medicalIllness" class="form-label">Medical Illness</label>
                            <select class="form-select" id="medicalIllness" name="medicalIllness" required>
                                <option value="" selected disabled hidden>Select Medical Illness</option>
                                <option value="Yes">Yes</option>
                                <option value="No">No</option>
                            </select>
                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="mb-3">
                            <label for="highRiskActivity" class="form-label">High Risk Activity</label>
                            <select class="form-select" id="highRiskActivity" name="highRiskActivity" required>
                                <option value="" selected disabled hidden>Select High Risk Activity</option>
                                <option value="Yes">Yes</option>
                                <option value="No">No</option>
                            </select>
                        </div>
                    </div>
                </div>
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