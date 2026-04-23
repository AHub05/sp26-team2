<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller Listings</title>
    <style>
        :root {
            color-scheme: light;
            font-family: Arial, Helvetica, sans-serif;
            --ink: #202124;
            --muted: #5f6368;
            --line: #dadce0;
            --surface: #ffffff;
            --page: #f7f8fa;
            --brand: #146c5f;
            --brand-dark: #0f554b;
            --accent: #b34a35;
        }

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            background: var(--page);
            color: var(--ink);
        }

        main {
            width: min(1120px, calc(100% - 32px));
            margin: 0 auto;
            padding: 32px 0 48px;
        }

        header {
            display: flex;
            align-items: flex-end;
            justify-content: space-between;
            gap: 16px;
            margin-bottom: 24px;
        }

        h1 {
            margin: 0;
            font-size: 32px;
            line-height: 1.15;
            letter-spacing: 0;
        }

        h2 {
            margin: 0 0 16px;
            font-size: 20px;
            letter-spacing: 0;
        }

        .toolbar,
        .setup,
        .empty,
        table {
            background: var(--surface);
            border: 1px solid var(--line);
            border-radius: 8px;
        }

        .toolbar,
        .setup,
        .empty {
            padding: 16px;
        }

        .toolbar {
            display: flex;
            align-items: end;
            justify-content: space-between;
            gap: 16px;
            margin-bottom: 16px;
        }

        form {
            margin: 0;
        }

        label {
            display: block;
            margin-bottom: 6px;
            color: var(--muted);
            font-size: 13px;
            font-weight: 700;
        }

        select,
        input {
            width: 100%;
            min-height: 40px;
            border: 1px solid #b8bec5;
            border-radius: 6px;
            padding: 8px 10px;
            font: inherit;
            background: #fff;
        }

        .seller-select {
            display: grid;
            grid-template-columns: minmax(220px, 360px) auto;
            align-items: end;
            gap: 10px;
        }

        .setup-grid {
            display: grid;
            grid-template-columns: repeat(4, minmax(0, 1fr));
            gap: 10px;
            align-items: end;
        }

        .button,
        button {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            min-height: 40px;
            border: 0;
            border-radius: 6px;
            padding: 8px 14px;
            color: #fff;
            background: var(--brand);
            font: inherit;
            font-weight: 700;
            text-decoration: none;
            cursor: pointer;
            white-space: nowrap;
        }

        .button:hover,
        button:hover {
            background: var(--brand-dark);
        }

        .button.secondary {
            background: #3c4043;
        }

        .button.danger {
            background: var(--accent);
        }

        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
            overflow: hidden;
        }

        th,
        td {
            padding: 13px 14px;
            border-bottom: 1px solid var(--line);
            text-align: left;
            vertical-align: middle;
        }

        th {
            color: var(--muted);
            font-size: 13px;
            background: #eef3f1;
        }

        tr:last-child td {
            border-bottom: 0;
        }

        .muted {
            color: var(--muted);
        }

        .actions {
            text-align: right;
        }

        @media (max-width: 760px) {
            header,
            .toolbar,
            .seller-select,
            .setup-grid {
                display: grid;
                grid-template-columns: 1fr;
            }

            table,
            thead,
            tbody,
            th,
            td,
            tr {
                display: block;
            }

            thead {
                display: none;
            }

            tr {
                border-bottom: 1px solid var(--line);
                padding: 10px 0;
            }

            td {
                border: 0;
                padding: 6px 14px;
            }

            .actions {
                text-align: left;
            }
        }
    </style>
</head>
<body>
<main>
    <header>
        <div>
            <h1>Seller Listings</h1>
            <#if selectedSeller??>
                <p class="muted">${selectedSeller.businessName!"Seller"} · ID ${selectedSeller.userId}</p>
            </#if>
        </div>
        <#if selectedSellerId??>
            <a class="button" href="/seller/listings/new?sellerId=${selectedSellerId}">+ New Listing</a>
        </#if>
    </header>

    <section class="toolbar">
        <form class="seller-select" method="get" action="/seller/listings">
            <div>
                <label for="sellerId">Seller</label>
                <select id="sellerId" name="sellerId" required>
                    <option value="">Select seller</option>
                    <#list sellers as seller>
                        <option value="${seller.userId}" <#if selectedSellerId?? && selectedSellerId == seller.userId>selected</#if>>
                            ${seller.businessName!"Seller"} (#${seller.userId})
                        </option>
                    </#list>
                </select>
            </div>
            <button type="submit">View Listings</button>
        </form>
    </section>

    <#if sellers?size == 0>
        <section class="setup">
            <h2>Demo Seller</h2>
            <form class="setup-grid" method="post" action="/seller/demo-seller">
                <div>
                    <label for="businessName">Business name</label>
                    <input id="businessName" name="businessName" value="Minerva Demo Shop" required>
                </div>
                <div>
                    <label for="email">Email</label>
                    <input id="email" name="email" type="email" value="seller@minervaslist.demo" required>
                </div>
                <div>
                    <label for="password">Password</label>
                    <input id="password" name="password" value="password" required>
                </div>
                <button type="submit">Create Seller</button>
            </form>
        </section>
    <#elseif selectedSellerId??>
        <#if listings?size == 0>
            <section class="empty">
                <p class="muted">No listings yet.</p>
                <a class="button" href="/seller/listings/new?sellerId=${selectedSellerId}">+ New Listing</a>
            </section>
        <#else>
            <table>
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Category</th>
                    <th>Condition</th>
                    <th>Price</th>
                    <th>Qty</th>
                    <th>Status</th>
                    <th class="actions">Actions</th>
                </tr>
                </thead>
                <tbody>
                <#list listings as listing>
                    <tr>
                        <td>${listing.title}</td>
                        <td>${listing.category}</td>
                        <td>${listing.itemCondition}</td>
                        <td>$${listing.price}</td>
                        <td>${listing.quantity}</td>
                        <td>${listing.status}</td>
                        <td class="actions">
                            <a class="button secondary" href="/seller/listings/${listing.listingId}/edit?sellerId=${selectedSellerId}">Edit</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </#if>
    <#else>
        <section class="empty">
            <p class="muted">Select a seller.</p>
        </section>
    </#if>
</main>
</body>
</html>
