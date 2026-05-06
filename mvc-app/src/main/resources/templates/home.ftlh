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
    <@renderNavbar userType="guest" />


    <div class="px-4 py-5 text-center">
        <img class="d-block mx-auto mb-4 h-25 w-25" src="https://ssb-landing.uncg.edu/content/images/uncg-genie-uncglogo.png">
        <h1>Welcome to <span class="fw-bold">Minerva's List</span></h1>
        <div class="col-lg-6 mx-auto">
            <p class="lead">A spartan marketplace</p>
            <div class="d-grid gap-2 d-sm-flex justify-content-sm-center"> 
                <a class="btn btn-primary btn-lg px-4 gap-3" href="/login">Log In</a>
                <a class="btn btn-outline-secondary btn-lg px-4" href="/signup">Sign Up</a> 
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>