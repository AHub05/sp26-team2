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
    <#include "/fragments/navbar.ftlh">
    <@renderNavbar userType="guest" />

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
                <div id="seller-alert" class="alert d-none" role="alert"></div>
                <form action="/seller/signup" method="post" enctype="multipart/form-data">
                  <div class="mb-3">
                    <label for="name" class="form-label">Business Name</label>
                    <input type="text" class="form-control" id="businessName" name="businessName" required>
                  </div>
                  <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                  </div>
                  <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                  </div>
                  <div class="mb-3">
                    <label for="profilePhoto" class="form-label">Profile Photo</label>
                    <input type="file" class="form-control" id="profilePhoto" name="profilePhoto" accept="image/*">
                    <div class="form-text">Use an image under 10MB for now.</div>
                  </div>
                  <div class="mb-3">
                    <label for="bio" class="form-label">Bio</label>
                    <textarea class="form-control" id="bio" name="bio" rows="3"></textarea>
                  </div>
                  <div class="mb-3">
                    <label for="servicesOffered" class="form-label">Services Offered</label>
                    <textarea class="form-control" id="servicesOffered" name="servicesOffered" rows="3"></textarea>
                  </div>
                  <button type="submit" class="btn btn-success">Sign Up</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
      (function () {
        const params = new URLSearchParams(window.location.search);
        const sellerTabTrigger = document.querySelector('a[href="#seller"]');
        const sellerAlert = document.getElementById('seller-alert');

        if (!sellerTabTrigger || !sellerAlert) {
          return;
        }

        if (params.get('error') === 'seller' && window.bootstrap) {
          bootstrap.Tab.getOrCreateInstance(sellerTabTrigger).show();
          sellerAlert.className = 'alert alert-danger';
          sellerAlert.textContent = 'Seller sign-up failed. Check your email, password, and photo size.';
        }
      }());
    </script>
</body>
</html>
