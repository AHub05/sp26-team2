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
    <#include "/fragments/navbar.ftlh">
    <@renderNavbar userType="guest" />

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
                                <a class="nav-link" id="farmer-tab" data-bs-toggle="tab" href="#seller">Seller</a>
                            </li>
                        </ul>
                    </div>
                    <div class="card-body">
                        <div class="tab-content">
                            <div class="tab-pane fade show active" id="customer">
                                <h3 class="card-title mb-4">Customer Sign In</h3>
                                <div id="customer-alert" class="alert d-none" role="alert"></div>
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
                            <div class="tab-pane fade" id="seller">
                                <h3 class="card-title mb-4">Seller Sign In</h3>
                                <div id="seller-alert" class="alert d-none" role="alert"></div>
                                <form action="/seller/signin" method="post">
                                    <div class="mb-3">
                                        <label for="sellerEmail" class="form-label">Email</label>
                                        <input type="email" class="form-control" id="sellerEmail" name="email" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="sellerPassword" class="form-label">Password</label>
                                        <input type="password" class="form-control" id="sellerPassword" name="password"
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
    <script>
        (function () {
            const params = new URLSearchParams(window.location.search);
            const customerAlert = document.getElementById('customer-alert');
            const sellerTabTrigger = document.querySelector('a[href="#seller"]');
            const sellerAlert = document.getElementById('seller-alert');

            if (!sellerTabTrigger || !sellerAlert || !customerAlert) {
                return;
            }

            const sellerError = params.get('error');
            const shouldOpenSellerTab = params.has('sellerCreated')
                || sellerError === 'seller'
                || sellerError === 'sellerMissing'
                || sellerError === 'sellerWrongType'
                || sellerError === 'sellerPassword';
            if (shouldOpenSellerTab && window.bootstrap) {
                bootstrap.Tab.getOrCreateInstance(sellerTabTrigger).show();
            }

            if (params.has('sellerCreated')) {
                sellerAlert.className = 'alert alert-success';
                sellerAlert.textContent = 'Seller account created. You can sign in now.';
            } else if (sellerError === 'sellerWrongType') {
                sellerAlert.className = 'alert alert-danger';
                sellerAlert.textContent = 'That email exists, but the account is not stored as a seller.';
            } else if (sellerError === 'sellerMissing') {
                sellerAlert.className = 'alert alert-danger';
                sellerAlert.textContent = 'Seller account was not found. Check the email address and seller record.';
            } else if (sellerError === 'sellerPassword') {
                sellerAlert.className = 'alert alert-danger';
                sellerAlert.textContent = 'Seller password did not match.';
            } else if (sellerError === 'seller') {
                sellerAlert.className = 'alert alert-danger';
                sellerAlert.textContent = 'Seller sign-in failed. Check your email and password.';
            } else if (params.get('error') === 'customer') {
                customerAlert.className = 'alert alert-danger';
                customerAlert.textContent = 'Customer sign-in failed. Check your email and password.';
            }
        }());
    </script>
</body>
</html>
