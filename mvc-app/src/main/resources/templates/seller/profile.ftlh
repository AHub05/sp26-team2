<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller Profile</title>
    <style>
        :root { font-family: Arial, Helvetica, sans-serif; --bg:#f7f8fa; --surface:#fff; --line:#dadce0; --muted:#5f6368; --ink:#202124; --brand:#146c5f; --brand-dark:#0f554b; --soft:#eef3f1; }
        * { box-sizing:border-box; }
        body { margin:0; background:var(--bg); color:var(--ink); }
        main { width:min(900px, calc(100% - 32px)); margin:0 auto; padding:0 0 48px; }
        form, .stats { background:var(--surface); border:1px solid var(--line); border-radius:8px; padding:20px; margin-bottom:18px; }
        .stats { border-top:4px solid var(--brand); }
        .alert { border-radius:8px; padding:12px 14px; margin-bottom:18px; font-weight:700; }
        .alert.success { color:#0f5132; background:#d1e7dd; border:1px solid #badbcc; }
        .alert.error { color:#842029; background:#f8d7da; border:1px solid #f5c2c7; }
        .grid { display:grid; grid-template-columns:1fr 1fr; gap:14px; }
        .full { grid-column:1 / -1; }
        label { display:block; margin-bottom:6px; color:var(--muted); font-size:13px; font-weight:700; }
        input, textarea { width:100%; min-height:42px; border:1px solid #b8bec5; border-radius:6px; padding:9px 10px; font:inherit; }
        input:focus, textarea:focus { outline:2px solid #cfe5df; border-color:var(--brand); }
        textarea { min-height:110px; resize:vertical; }
        .actions { display:flex; justify-content:flex-end; gap:10px; margin-top:18px; }
        .button, button { display:inline-flex; align-items:center; justify-content:center; min-height:40px; border:0; border-radius:6px; padding:8px 14px; color:#fff; background:var(--brand); text-decoration:none; font-weight:700; cursor:pointer; }
        .button:hover, button:hover { background:var(--brand-dark); }
        .muted { color:var(--muted); }
    </style>
</head>
<body>
<#include "/fragments/navbar.ftlh">
<@renderNavbar userType="seller" />
<main>
    <#if RequestParameters.updated??>
        <div class="alert success">Profile updated.</div>
    </#if>
    <#if RequestParameters.error?? && RequestParameters.error == "update">
        <div class="alert error">Profile could not be updated. Check the server log for details.</div>
    </#if>

    <div class="stats">
        <h1>${seller.businessName!"Seller Profile"}</h1>
        <p class="muted">${seller.email}</p>
        <p>${stats.totalListings} listings · ${stats.totalReviews} reviews · ${stats.averageRating?string["0.0"]} average rating</p>
        <#if seller.hasStoredProfilePhoto()>
            <img src="/seller/photo/${seller.userId}" alt="${seller.businessName!'Seller'} profile photo"
                 style="width:120px;height:120px;object-fit:cover;border-radius:999px;border:1px solid #dadce0;">
        <#else>
            <div style="width:120px;height:120px;border-radius:999px;border:1px solid #dadce0;background:#eceff1;display:flex;align-items:center;justify-content:center;font-size:38px;font-weight:700;color:#5f6368;">
                ${seller.businessName?has_content?then(seller.businessName?substring(0, 1)?upper_case, "S")}
            </div>
        </#if>
    </div>

    <form method="post" action="/seller/profile" enctype="multipart/form-data">
        <div class="grid">
            <div>
                <label for="businessName">Business Name</label>
                <input id="businessName" name="businessName" value="${seller.businessName!}" required>
            </div>
            <div>
                <label for="email">Email</label>
                <input id="email" name="email" type="email" value="${seller.email!}" required>
            </div>
            <div>
                <label for="password">Password</label>
                <input id="password" name="password" type="password" value="${seller.password!}" required>
            </div>
            <div>
                <label for="profilePhoto">Profile Photo</label>
                <input id="profilePhoto" name="profilePhoto" type="file" accept="image/*">
            </div>
            <div class="full">
                <label for="bio">Bio</label>
                <textarea id="bio" name="bio">${seller.bio!}</textarea>
            </div>
            <div class="full">
                <label for="servicesOffered">Services Offered</label>
                <textarea id="servicesOffered" name="servicesOffered">${seller.servicesOffered!}</textarea>
            </div>
        </div>
        <div class="actions">
            <a class="button" href="/seller/home">Back</a>
            <button type="submit">Save Profile</button>
        </div>
    </form>
</main>
</body>
</html>
