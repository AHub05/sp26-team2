<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Browse Listings | Minerva's List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <style>
        .listing-photo {
            width: 100%;
            height: 240px;
            object-fit: cover;
            display: block;
            background: #f8f9fa;
        }

        .listing-photo-empty {
            height: 240px;
            display: flex;
            align-items: center;
            justify-content: center;
            background: #f8f9fa;
        }
    </style>
</head>
<body>
    <#include "/fragments/navbar.ftlh">
    <@renderNavbar userType="customer" />
    <div class="container py-4">
        <div class="d-flex justify-content-between align-items-end mb-4">
            <div>
                <h1 class="mb-1">Listings</h1>
                <p class="text-muted mb-0">Browse what sellers have posted.</p>
            </div>
        </div>

        <#if productListings?size == 0>
            <div class="alert alert-secondary">No listings are available yet.</div>
        <#else>
            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
            <#list productListings as listing>
                <div class="col">
                    <a href="/customer/shop/${listing.listingId}" class="text-decoration-none text-dark">
                        <div class="card shadow-sm h-100">
                            <#if listing.hasStoredPhoto()>
                                <img src="/seller/listings/${listing.listingId}/photo" class="card-img-top listing-photo" alt="${listing.title}">
                            <#else>
                                <div class="card-img-top listing-photo-empty">
                                    <span class="text-muted">No image uploaded</span>
                                </div>
                            </#if>
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-start gap-3">
                                    <h4 class="card-title h5 mb-1">${listing.title}</h4>
                                    <span class="fw-bold">$${listing.price}</span>
                                </div>
                                <p class="text-muted small mb-2">${listing.category} · ${listing.itemCondition}</p>
                                <p class="card-text mb-0">${listing.description!""}</p>
                            </div>
                        </div>
                    </a>
                </div>
            </#list>
            </div>
        </#if>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
