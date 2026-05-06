<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Minerva's List</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>

<body>
    <!-- Navbar -->
    <#include "/fragments/navbar.ftlh">
    <@renderNavbar userType="customer" />

    <!-- Content -->
    <section class="hero">
        <div class="hero-content">
            <h1>Welcome to Minerva's List</h1>
            <p>The Marketplace for Spartans</p>
            <button onclick="location.href='customer-prototype/listing.html'">Browse Listings</button>
        </div>
    </section>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>