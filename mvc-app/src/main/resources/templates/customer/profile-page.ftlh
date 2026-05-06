<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
    <#include "/fragments/navbar.ftlh">
    <@renderNavbar userType="customer" />

    <div class="container">
        <div class="card">
            <div class="row g-0">
                <div class="col-sm-4">
                    <div class="card-body">
                        <img src="https://placehold.co/200" class="img rounded-circle mx-auto d-block w-25" alt="${customer.name}'s profile photo">
                    </div>
                </div>
                <div class="col-sm-8 d-flex align-items-center">
                    <h5 class="card-title">${customer.name}</h5>
                </div>
            </div>
        </div>
    </div>

    <div class="container mt-5">
        <h2>Purchases</h2>
        <hr>
        <div class="row text-center">
            <#if purchases?? && purchases?size gt 0>
                <#list purchases as purchase>
                    <div class="col">
                        <div class="card mb-4 rounded-3 shadow-sm">
                            <div class="card-header py-3">
                                <h4 class="my-0 fw-normal">${purchase.listing.title}</h4>
                            </div>
                            <div class="card-body">
                                <h1 class="card-title pricing-card-title">$${purchase.totalPrice}</h1>
                                <ul class="list-unstyled mt-3 mb-4">
                                    <li>Quantity: ${purchase.quantity}</li>
                                    <li>${purchase.listing.category}</li>
                                    <li>${purchase.purchaseDate}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </#list>
            <#else>
                <div class="alert alert-dark">
                    <h5>No Purchases Made</h5>
                    <p>There are no purchases to display. Go buy something Spartan!</p>
                </div>
            </#if>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>