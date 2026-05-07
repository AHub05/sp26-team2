<#macro renderNavbar userType="guest">
    <style>
        .minerva-nav {
            --nav-ink: #202124;
            --nav-muted: #5f6368;
            --nav-brand: #146c5f;
            --nav-brand-dark: #0f554b;
            --nav-line: #dadce0;
            --nav-surface: #f8f9fa;
            background: var(--nav-surface);
            border-bottom: 1px solid var(--nav-line);
            margin-bottom: 48px;
        }

        .minerva-nav__inner {
            width: min(1120px, calc(100% - 32px));
            min-height: 68px;
            margin: 0 auto;
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 24px;
        }

        .minerva-nav__brand {
            color: var(--nav-ink);
            font-size: 22px;
            font-weight: 800;
            letter-spacing: 0;
            text-decoration: none;
            white-space: nowrap;
        }

        .minerva-nav__links {
            display: flex;
            align-items: center;
            justify-content: flex-end;
            gap: 4px;
            margin: 0;
            padding: 0;
            list-style: none;
            flex-wrap: wrap;
        }

        .minerva-nav__link {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            min-height: 38px;
            padding: 8px 12px;
            border-radius: 6px;
            color: var(--nav-muted);
            font-weight: 700;
            text-decoration: none;
            line-height: 1;
        }

        .minerva-nav__link:hover {
            color: var(--nav-ink);
            background: #eceff1;
        }

        .minerva-nav__link--primary {
            color: var(--nav-muted);
            background: transparent;
        }

        .minerva-nav__link--primary:hover {
            color: var(--nav-ink);
            background: #eceff1;
        }

        .minerva-nav__link--quiet {
            color: #b34a35;
        }

        .minerva-nav__link--quiet:hover {
            color: #9c3f2c;
            background: #f8ebe8;
        }

        @media (max-width: 680px) {
            .minerva-nav__inner {
                min-height: auto;
                padding: 14px 0;
                display: grid;
                grid-template-columns: 1fr;
                gap: 12px;
            }

            .minerva-nav__links {
                justify-content: flex-start;
            }
        }
    </style>
    <nav class="minerva-nav">
        <div class="minerva-nav__inner">
            <a class="minerva-nav__brand" href="/">Minerva's List</a>
            <div>
                <ul class="minerva-nav__links">
                    <#if userType == "customer">
                    <li>
                        <a class="minerva-nav__link" href="/customer/home">Home</a>
                    </li>
                    <li>
                        <a class="minerva-nav__link" href="/customer/browse">Shop</a>
                    </li>
                    <li>
                        <a class="minerva-nav__link" href="/customer/profile">Purchases</a>
                    </li>
                    <li>
                        <a class="minerva-nav__link" href="/customer/profile">Profile</a>
                    </li>
                    <li>
                        <a class="minerva-nav__link minerva-nav__link--quiet" href="/customer/logout">Logout</a>
                    </li>
                    <#elseif userType == "seller">
                    <li>
                        <a class="minerva-nav__link" href="/seller/home">Home</a>
                    </li>
                    <li>
                        <a class="minerva-nav__link" href="/seller/listings">Listings</a>
                    </li>
                    
                    <li>
                        <a class="minerva-nav__link" href="/seller/profile">Profile</a>
                    </li>
                    <li>
                        <a class="minerva-nav__link minerva-nav__link--quiet" href="/seller/logout">Logout</a>
                    </li>
                    <#else>
                    <li>
                        <a class="minerva-nav__link" href="/">Home</a>
                    </li>
                    <li>
                        <a class="minerva-nav__link" href="/login">Log In</a>
                    </li>
                    <li>
                        <a class="minerva-nav__link minerva-nav__link--primary" href="/signup">Sign Up</a>
                    </li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>
</#macro>
