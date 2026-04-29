<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log In | Minerva's List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
    <nav class="navbar navbar-expand-lg bg-boddy-tertiary mb-4">
        <div class="container">
            <a class="navbar-brand" href="/">Minerva's List</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/signup">Sign Up</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <ul class="nav nav-tabs card-header-tabs">
                            <li class="nav-item">
                                <a class="nav-link active" id="customer-tab" data-bs-toggle="tab"
                                    href="#customer">Customer</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="farmer-tab" data-bs-toggle="tab" href="#seller">Farmer</a>
                            </li>
                        </ul>
                    </div>
                    <div class="card-body">
                        <div class="tab-content">
                            <div class="tab-pane fade show active" id="customer">
                                <h3 class="card-title mb-4">Customer Sign In</h3>
                                <form action="/customer/login" method="post">
                                    <div class="mb-3">
                                        <label for="customerEmail" class="form-label">Email</label>
                                        <input type="email" class="form-control" id="customerEmail" name="email" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="customerPassword" class="form-label">Password</label>
                                        <input type="password" class="form-control" id="customerPassword" name="password"
                                            required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Sign In</button>
                                </form>
                                <div class="mt-3">
                                    <a href="/customer/signup">Need an account? Sign up as a customer</a>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="farmer">
                                <h3 class="card-title mb-4">Seller Sign In</h3>
                                <form action="/farmer/signin" method="post">
                                    <div class="mb-3">
                                        <label for="farmerEmail" class="form-label">Email</label>
                                        <input type="email" class="form-control" id="farmerEmail" name="email" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="farmerPassword" class="form-label">Password</label>
                                        <input type="password" class="form-control" id="farmerPassword" name="password"
                                            required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Sign In</button>
                                </form>
                                <div class="mt-3">
                                    <a href="/signup">Need an account? Sign up today!</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>