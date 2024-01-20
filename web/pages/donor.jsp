<div class="card border-0 shadow mb-3">
    <div class="card-body d-flex justify-content-between align-items-center">
        <p class="m-0 fs-4 fw-semibold">Donor</p>

        <button type="button" class="btn btn-sm btn-primary shadow-0 text-capitalize ms-2" data-bs-toggle="modal" data-bs-target="#createDonorModal">
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
                        <th scope="col">Action</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>100</td>
                        <td>171099-12-1233</td>
                        <td>John Doe</td>
                        <td>17/10/1999</td>
                        <td>Male</td>
                        <td>B</td>
                        <td>1</td>
                        <td>2</td>
                        <td>
                            <a href="#">Edit</a>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>101</td>
                        <td>210801-12-1234</td>
                        <td>Jane Doe</td>
                        <td>21/08/2001</td>
                        <td>Female</td>
                        <td>B</td>
                        <td>1</td>
                        <td>0</td>
                        <td>
                            <a href="#">Edit</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="createDonorModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="createDonorModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="createDonorModalLabel">Create New Donor</h1>
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

                <div class="row">
                    <div class="col-12 col-lg-6">
                        <div class="mb-3">
                            <label for="icNumber" class="form-label">IC Number</label>
                            <input type="text" class="form-control" id="icNumber" name="icNumber" placeholder="e.g. 123456-12-1234" required>
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
                            <label for="dateOfBirth" class="form-label">Date of Birth</label>
                            <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" required>
                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="mb-3">
                            <label for="gender" class="form-label">Gender</label>

                            <div class="d-flex">
                                <div class="form-check me-3">
                                    <input class="form-check-input" type="radio" name="gender" id="genderMale" value="male" required>
                                    <label class="form-check-label" for="genderMale">Male</label>
                                </div>

                                <div class="form-check me-3">
                                    <input class="form-check-input" type="radio" name="gender" id="genderFemale" value="female">
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
                            <label for="bloodType" class="form-label">Blood Type</label>
                            <select class="form-select" id="bloodType" name="bloodType" required>
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
    </div>
</div>