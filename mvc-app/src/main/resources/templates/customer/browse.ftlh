<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Browse Listings | Minerva's List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
    <#include "/fragments/navbar.ftlh">
    <@renderNavbar userType="customer" />
    <h1>Listings</h1>

    <#list productListings as listing>
        <a href="#">
            <div class="col">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h4>${listing.title}</h4>
                        <p>${listing.description}</p>
                    </div>
                </div>
            </div>
        </a>
    </#list>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>