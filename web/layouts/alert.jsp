<div class="container fixed-top mt-5">
    <div id="errorAlert" class="alert alert-danger alert-dismissible d-none" role="alert">
        <span id="errorAlertText"></span>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <div id="successAlert" class="alert alert-success alert-dismissible d-none" role="alert">
        <span id="successAlertText"></span>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</div>

<script>
    const urlParams = new URLSearchParams(window.location.search);
    const error = urlParams.get('error');
    const success = urlParams.get('success');

    if (error) {
        window.history.replaceState({}, document.title, window.location.pathname);
        document.getElementById('errorAlert').classList.remove('d-none');
        document.getElementById('errorAlertText').innerHTML = error;

        setTimeout(() => {
            document.getElementById('errorAlert').classList.add('d-none');
        }, 3000);
    }

    if (success) {
        window.history.replaceState({}, document.title, window.location.pathname);
        document.getElementById('successAlert').classList.remove('d-none');
        document.getElementById('successAlertText').innerHTML = success;

        setTimeout(() => {
            document.getElementById('successAlert').classList.add('d-none');
        }, 3000);
    }
</script>