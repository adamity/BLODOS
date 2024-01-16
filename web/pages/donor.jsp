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
                            <a href="#">View</a>
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
                            <a href="#">View</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="createDonorModal" tabindex="-1" aria-labelledby="createDonorModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="createDonorModalLabel">Create New Donor</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <div class="modal-body">
                ...
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </div>
</div>