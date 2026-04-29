<#macro renderNavbar userType="customer">
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
                    <#if userType == "customer">
                    <li class="nav-item">
                        <a class="nav-link" href="/cusomer/">Shop</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">Reviews</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/login">Logout</a>
                    </li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>
</#macro>