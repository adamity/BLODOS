<div class="card border-0 shadow mb-3">
    <div class="card-body d-flex justify-content-between align-items-center">
        <p class="m-0 fs-4 fw-semibold">Donation Type</p>

        <button type="button" class="btn btn-sm btn-primary shadow-0 text-capitalize ms-2" data-bs-toggle="modal" data-bs-target="#createDonationTypeModal">
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
                            <a href="#">Edit</a>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>101</td>
                        <td>Platelet</td>
                        <td>1</td>
                        <td>
                            <a href="#">Edit</a>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">3</th>
                        <td>102</td>
                        <td>Plasma</td>
                        <td>0</td>
                        <td>
                            <a href="#">Edit</a>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">4</th>
                        <td>103</td>
                        <td>Red Blood Cell</td>
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

<div class="modal fade" id="createDonationTypeModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="createDonationTypeModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="createDonationTypeModalLabel">Create New Donation Type</h1>
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