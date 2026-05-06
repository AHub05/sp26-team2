<#macro renderNavbar userType="guest">
    <nav class="navbar navbar-expand-lg bg-boddy-tertiary mb-4">
        <div class="container">
            <a class="navbar-brand" href="/">Minerva's List</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <#if userType == "customer">
                    <li class="nav-item">
                        <a class="nav-link" href="/customer/home">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/customer/">Shop</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">Reviews</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/customer/profile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/customer/logout">Logout</a>
                    </li>
                    <#elseif userType == "seller">
                    <li class="nav-item">
                        <a class="nav-link" href="/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/customer/">Shop</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">Reviews</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/customer/profile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/customer/logout">Logout</a>
                    </li>
                    <#else>
                    <li class="nav-item">
                        <a class="nav-link" href="/">Home</a>
                    </li>
                    <li>
                        <a class="nav-link" href="/login">Log In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/signup">Sign Up</a>
                    </li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>
</#macro>