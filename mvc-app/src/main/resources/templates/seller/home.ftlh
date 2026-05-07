<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller Home</title>
    <style>
        :root { font-family: Arial, Helvetica, sans-serif; --bg:#f7f8fa; --surface:#fff; --line:#dadce0; --muted:#5f6368; --ink:#202124; --brand:#146c5f; --brand-dark:#0f554b; --soft:#eef3f1; }
        * { box-sizing: border-box; }
        body { margin:0; background:var(--bg); color:var(--ink); }
        main { width:min(1080px, calc(100% - 32px)); margin:0 auto; padding:0 0 48px; }
        h1,h2 { margin:0 0 12px; letter-spacing:0; }
        .hero, .stats, .listings { background:var(--surface); border:1px solid var(--line); border-radius:8px; padding:20px; margin-bottom:18px; }
        .hero { border-top:4px solid var(--brand); }
        .stats-grid { display:grid; grid-template-columns:repeat(5, minmax(0, 1fr)); gap:12px; }
        .stat { border:1px solid var(--line); border-radius:8px; padding:14px; background:var(--soft); }
        .stat strong { color:var(--brand-dark); font-size:24px; }
        .muted { color:var(--muted); }
        .actions { display:flex; gap:10px; margin-top:14px; }
        .button { display:inline-flex; align-items:center; justify-content:center; min-height:40px; border-radius:6px; padding:8px 14px; background:var(--brand); color:#fff; text-decoration:none; font-weight:700; }
        .button:hover { background:var(--brand-dark); }
        .listing-grid { display:grid; grid-template-columns:repeat(3, minmax(0, 1fr)); gap:14px; }
        .listing-card { border:1px solid var(--line); border-radius:8px; overflow:hidden; background:#fff; }
        .listing-photo { width:100%; height:150px; object-fit:cover; display:block; background:#f8f9fa; }
        .listing-photo-empty { height:150px; display:flex; align-items:center; justify-content:center; color:var(--muted); background:#f8f9fa; }
        .listing-body { padding:12px; }
        .listing-body strong { display:block; margin-bottom:4px; }
        @media (max-width: 760px) { .stats-grid { grid-template-columns:1fr 1fr; } .listing-grid { grid-template-columns:1fr; } }
    </style>
</head>
<body>
<#include "/fragments/navbar.ftlh">
<@renderNavbar userType="seller" />
<main>
    <section class="hero">
        <h1>${seller.businessName!"Seller Dashboard"}</h1>
        <p class="muted">${seller.email}</p>
        <p>${seller.bio!"Add your seller bio from the profile page."}</p>
        <div class="actions">
            <a class="button" href="/seller/profile">Edit Profile</a>
            <a class="button" href="/seller/listings">Manage Listings</a>
        </div>
    </section>

    <section class="stats">
        <h2>Stats</h2>
        <div class="stats-grid">
            <div class="stat"><strong>${stats.totalListings}</strong><div class="muted">Total listings</div></div>
            <div class="stat"><strong>${stats.activeListings}</strong><div class="muted">Active listings</div></div>
            <div class="stat"><strong>${stats.completedTransactions}</strong><div class="muted">Transactions</div></div>
            <div class="stat"><strong>${stats.totalReviews}</strong><div class="muted">Reviews</div></div>
            <div class="stat"><strong>${stats.averageRating?string["0.0"]}</strong><div class="muted">Average rating</div></div>
        </div>
    </section>

    <section class="listings">
        <h2>Recent Listings</h2>
        <#if recentListings?size == 0>
            <p class="muted">No listings yet.</p>
        <#else>
            <div class="listing-grid">
                <#list recentListings as listing>
                    <article class="listing-card">
                        <#if listing.hasStoredPhoto()>
                            <img class="listing-photo" src="/seller/listings/${listing.listingId}/photo" alt="${listing.title} photo">
                        <#else>
                            <div class="listing-photo-empty">No image uploaded</div>
                        </#if>
                        <div class="listing-body">
                            <strong>${listing.title}</strong>
                            <div class="muted">${listing.status} · $${listing.price}</div>
                        </div>
                    </article>
                </#list>
            </div>
        </#if>
    </section>
</main>
</body>
</html>
