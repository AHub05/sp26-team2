<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller Reviews</title>
    <style>
        :root { font-family: Arial, Helvetica, sans-serif; --bg:#f7f8fa; --surface:#fff; --line:#dadce0; --muted:#5f6368; --ink:#202124; --brand:#146c5f; --brand-dark:#0f554b; --soft:#eef3f1; }
        * { box-sizing:border-box; }
        body { margin:0; background:var(--bg); color:var(--ink); }
        main { width:min(960px, calc(100% - 32px)); margin:0 auto; padding:0 0 48px; }
        h1 { margin:0 0 18px; letter-spacing:0; }
        .review { background:var(--surface); border:1px solid var(--line); border-radius:8px; padding:18px; margin-bottom:16px; }
        .review strong { color:var(--brand-dark); }
        textarea { width:100%; min-height:96px; border:1px solid #b8bec5; border-radius:6px; padding:10px; font:inherit; }
        textarea:focus { outline:2px solid #cfe5df; border-color:var(--brand); }
        button { min-height:40px; border:0; border-radius:6px; padding:8px 14px; color:#fff; background:var(--brand); font:inherit; font-weight:700; cursor:pointer; margin-top:10px; }
        button:hover { background:var(--brand-dark); }
        .muted { color:var(--muted); }
    </style>
</head>
<body>
<#include "/fragments/navbar.ftlh">
<@renderNavbar userType="seller" />
<main>
    <h1>Seller Reviews</h1>
    <#if reviews?size == 0>
        <p class="muted">No reviews yet.</p>
    <#else>
        <#list reviews as review>
            <section class="review">
                <strong>Rating: ${review.rating}/5</strong>
                <p>${review.comment}</p>
                <p class="muted">Customer: ${review.customer.name!"Customer"}</p>
                <form method="post" action="/seller/reviews/${review.reviewId}/reply">
                    <label for="reply-${review.reviewId}">Seller Reply</label>
                    <textarea id="reply-${review.reviewId}" name="sellerReply">${review.sellerReply!}</textarea>
                    <button type="submit"><#if review.sellerReply??>Update Reply<#else>Reply</#if></button>
                </form>
            </section>
        </#list>
    </#if>
</main>
</body>
</html>
