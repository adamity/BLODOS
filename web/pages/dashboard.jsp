<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="card border-0 shadow mb-3">
    <div class="card-body d-flex justify-content-between align-items-center">
        <p class="m-0 fs-4 fw-semibold">Dashboard</p>
    </div>
</div>

<div class="card border-0 shadow">
    <div class="card-body">
        <p class="fs-5 fw-semibold text-muted">Donor Statistic</p>
        <div class="row mb-4">
            <div class="col-12 col-lg-4">
                <div class="card shadow-0 card-wisteria h-100">
                    <div class="card-body">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-people-fill fs-4 me-2"></i>
                            <span class="fw-semibold">TOTAL DONOR</span>
                        </div>
                        <hr>
                        <p class="mb-0 fs-1 fw-bold">
                            ${totalDonor != null ? totalDonor : 0}
                        </p>
                    </div>
                </div>
            </div>

            <div class="col-12 col-lg-4">
                <div class="card shadow-0 card-olivine h-100">
                    <div class="card-body">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-person-fill-check fs-4 me-2"></i>
                            <span class="fw-semibold">TOTAL ELIGIBLE DONOR</span>
                        </div>
                        <hr>
                        <p class="mb-0 fs-1 fw-bold">
                            ${totalEligibleDonor != null ? totalEligibleDonor : 0}
                        </p>
                    </div>
                </div>
            </div>

            <div class="col-12 col-lg-4">
                <div class="card shadow-0 card-sunglo h-100">
                    <div class="card-body">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-person-fill-dash fs-4 me-2"></i>
                            <span class="fw-semibold">TOTAL INELIGIBLE DONOR</span>
                        </div>
                        <hr>
                        <p class="mb-0 fs-1 fw-bold">
                            ${totalIneligibleDonor != null ? totalIneligibleDonor : 0}
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <p class="fs-5 fw-semibold text-muted">Donation Statistic</p>
        <div class="row mb-4">
            <div class="col-12 col-lg-4">
                <div class="card shadow-0 card-danube text-white h-100">
                    <div class="card-body">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-file-medical-fill fs-4 me-2"></i>
                            <span class="fw-semibold">TOTAL DONATION</span>
                        </div>
                        <hr>
                        <p class="mb-0 fs-1 fw-bold">
                            ${totalDonation != null ? totalDonation : 0}
                        </p>
                    </div>
                </div>
            </div>

            <div class="col-12 col-lg-4">
                <div class="card shadow-0 card-danube h-100">
                    <div class="card-body">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-heart-pulse-fill fs-4 me-2"></i>
                            <span class="fw-semibold">TOP DONATED BLOOD TYPE</span>
                        </div>
                        <hr>
                        <p class="mb-0 fs-1 fw-bold">
                            ${topDonatedBloodType != null ? topDonatedBloodType : 'N/A'}
                        </p>
                    </div>
                </div>
            </div>

            <div class="col-12 col-lg-4">
                <div class="card shadow-0 card-danube text-white h-100">
                    <div class="card-body">
                        <div class="d-flex align-items-center">
                            <i class="bi bi-clipboard2-pulse-fill fs-4 me-2"></i>
                            <span class="fw-semibold">TOP 3 DONATION TYPE</span>
                        </div>
                        <hr>

                        <ul class="list-group list-group-flush rounded">
                            <c:forEach items="${topDonationTypes}" var="topDonationType" varStatus="status">
                                <c:if test="${status.index < 3}">
                                    <li class="list-group-item">${topDonationType}</li>
                                </c:if>
                            </c:forEach>
                            <c:if test="${empty topDonationTypes}">
                                <li class="list-group-item">N/A</li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>