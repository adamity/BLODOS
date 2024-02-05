<div style="height: 75vh;">
    <div class="d-flex justify-content-center align-items-center h-100">
        <div class="card border-0 shadow">
        <div class="card-body mx-4 my-3">
            <div class="text-center">
                <p class="fs-4 fw-bold text-seance m-0">BLODOS</p>
                <p class="fs-6 fw-light text-seance">Blood Donation System</p>
            </div>

            <form action="auth/login" method="post" class="mt-5">
                <div class="mb-3">
                    <label for="email" class="form-label">Username<span class="text-danger">*</span></label>
                    <input type="text" class="form-control" id="username" name="username" required />
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Password<span class="text-danger">*</span></label>
                    <input type="password" class="form-control" id="password" name="password" required />
                </div>

                <button type="submit" class="btn btn-sm btn-dark text-capitalize px-4 float-end">Sign In</button>
            </form>
        </div>
        </div>
    </div>
</div>