<div class="card border-0 shadow mb-3">
    <div class="card-body d-flex justify-content-between align-items-center">
        <p class="m-0 fs-4 fw-semibold">Donation</p>

        <button type="button" class="btn btn-sm btn-primary shadow-0 text-capitalize ms-2" data-bs-toggle="modal" data-bs-target="#createDonationModal">
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
                            <a href="#">View</a>
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
                            <a href="#">View</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="modal fade" id="createDonationModal" tabindex="-1" aria-labelledby="createDonationModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="createDonationModalLabel">Create New Donation</h1>
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