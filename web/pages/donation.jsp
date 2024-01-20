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
                        <th scope="col">Action</th>
                    </tr>
                </thead>

                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>009</td>
                        <td>John Doe</td>
                        <td>17/10/2022</td>
                        <td>09:30</td>
                        <td>470</td>
                        <td>Completed</td>
                        <td>
                            <button type="button" class="btn btn-sm btn-link text-capitalize p-0" onclick="upsertInit('009')">Edit</button>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>010</td>
                        <td>John Doe</td>
                        <td>17/10/2023</td>
                        <td>09:30</td>
                        <td>470</td>
                        <td>Completed</td>
                        <td>
                            <button type="button" class="btn btn-sm btn-link text-capitalize p-0" onclick="upsertInit('010')">Edit</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="upsertDonationModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="upsertDonationModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="upsertDonationModalLabel">Create New Donation</h1>
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
                            <label for="date" class="form-label">Date</label>
                            <input type="date" class="form-control" id="date" name="date" required>
                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="mb-3">
                            <label for="time" class="form-label">Time</label>
                            <input type="time" class="form-control" id="time" name="time" required>
                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="mb-3">
                            <label for="quantity" class="form-label">Quantity (ml)</label>
                            <input type="number" class="form-control" id="quantity" name="quantity" placeholder="e.g. 470" required>
                        </div>
                    </div>

                    <div class="col-12 col-lg-6">
                        <div class="mb-3">
                            <label for="status" class="form-label">Status</label>
                            <select class="form-select" id="status" name="status" required>
                                <option value="" selected disabled hidden>Select Status</option>
                                <option value="Confirmed">Confirmed</option>
                                <option value="Completed">Completed</option>
                                <option value="Cancelled">Cancelled</option>
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

<script>
    function upsertInit(id = null) {
        if (id) {
            console.log('Edit');
        } else {
            console.log('Create');
        }
    }
</script>