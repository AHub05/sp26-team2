<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
    <!-- Navbar -->
    <#include "/fragments/navbar.ftlh">
    <@renderNavbar userType="customer" />

    <div class="container">
        <div class="row">

            <!-- Item Picture -->
            <div class="col">
                <img src="https://placehold.co/400" class="col mx-auto d-block">
            </div>

            <!-- Item Information -->
            <div class="col">
                <h2>${listing.title}</h2>
                <h1>$${listing.price}</h1> 
                <hr>
                <p>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-award"
                        viewBox="0 0 16 16">
                        <path
                            d="M9.669.864 8 0 6.331.864l-1.858.282-.842 1.68-1.337 1.32L2.6 6l-.306 1.854 1.337 1.32.842 1.68 1.858.282L8 12l1.669-.864 1.858-.282.842-1.68 1.337-1.32L13.4 6l.306-1.854-1.337-1.32-.842-1.68zm1.196 1.193.684 1.365 1.086 1.072L12.387 6l.248 1.506-1.086 1.072-.684 1.365-1.51.229L8 10.874l-1.355-.702-1.51-.229-.684-1.365-1.086-1.072L3.614 6l-.25-1.506 1.087-1.072.684-1.365 1.51-.229L8 1.126l1.356.702z" />
                        <path d="M4 11.794V16l4-1 4 1v-4.206l-2.018.306L8 13.126 6.018 12.1z" />
                    </svg> ${listing.itemCondition} 
                    <br>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-tag"
                        viewBox="0 0 16 16">
                        <path d="M6 4.5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m-1 0a.5.5 0 1 0-1 0 .5.5 0 0 0 1 0" />
                        <path
                            d="M2 1h4.586a1 1 0 0 1 .707.293l7 7a1 1 0 0 1 0 1.414l-4.586 4.586a1 1 0 0 1-1.414 0l-7-7A1 1 0 0 1 1 6.586V2a1 1 0 0 1 1-1m0 5.586 7 7L13.586 9l-7-7H2z" />
                    </svg> ${listing.category}
                </p>
                <hr>
                <h3>Description</h3>
                <p>${listing.description}</p>
                <hr>
                <h3>Posted by:</h3>
                <div class="container">
                    <div class="card">
                        <div class="row g-0">
                            <div class="col-sm-4">
                                <div class="card-body">
                                    <img src="https://placehold.co/200" class="img rounded-circle mx-auto d-block w-25"
                                        alt="${listing.seller.businessName}'s profile photo">
                                </div>
                            </div>
                            <div class="col-sm-8 d-flex align-items-center">
                                <h5 class="card-title my-auto">${listing.seller.businessName}</h5>
                            </div>
                        </div>
                    </div>
                </div>
                    <form action="/customer/shop/${listing.listingId}/purchase" method="post" class="row mt-3">
                        <div class="col">
                            <label for="quantity" class="col-form-label">Quantity</label>
                            <input type="number" id="quantity" name="quantity" class="form-control" required>
                            <div class="invalid-feedback">
                                Please choose a quantity.
                            </div>
                        </div>
                        <div class="col d-grid">
                            <button type="submit" class="btn btn-success mt-4">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bag-check"
                                    viewBox="0 0 16 16">
                                    <path fill-rule="evenodd"
                                        d="M10.854 8.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L7.5 10.793l2.646-2.647a.5.5 0 0 1 .708 0" />
                                    <path
                                        d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1m3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1z" />
                                </svg>
                                Purchase
                            </button>
                        </div>
                    </form>
            </div>
        </div>
    </div>
    
     <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>