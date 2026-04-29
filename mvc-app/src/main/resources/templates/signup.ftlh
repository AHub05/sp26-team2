<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up | Minerva's List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
    <nav class="navbar navbar-expand-lg bg-body-tertiary mb-4">
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
                        <a class="nav-link" href="/login">Log In</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>   

    <!-- Login/Signup -->
    <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-header">
            <ul class="nav nav-tabs card-header-tabs">
              <li class="nav-item">
                <a class="nav-link active" id="customer-tab" data-bs-toggle="tab" href="#customer">Customer</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" id="seller-tab" data-bs-toggle="tab" href="#seller">Seller</a>
              </li>
            </ul>
          </div>
          <div class="card-body">
            <div class="tab-content">
              <div class="tab-pane fade show active" id="customer">
                <h3 class="card-title mb-4">Customer Sign Up</h3>
                <form action="/customer/signup" method="post">
                  <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                  </div>
                  <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                  </div>
                  <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                  </div>
                  <input type="text" id="role" name="role" value="CUSTOMER" hidden>
                  <button type="submit" class="btn btn-success">Sign Up</button>
                </form>
              </div>
              <div class="tab-pane fade" id="seller">
                <h3 class="card-title mb-4">Seller Sign Up</h3>
                <form action="/seller/signup" method="post">
                  <div class="mb-3">
                    <label for="name" class="form-label">Business Name</label>
                    <input type="text" class="form-control" id="business" name="business" required>
                  </div>
                  <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                  </div>
                  <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="passwordHash" name="passwordHash" required>
                  </div>
                  <input type="text" id="role" name="role" value="SELLER" hidden>
                  <input type="text" id="status" name="status" value="ACTIVE" hidden>
                  <button type="submit" class="btn btn-success">Sign Up</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>