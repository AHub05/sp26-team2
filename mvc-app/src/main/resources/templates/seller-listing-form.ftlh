<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${formTitle}</title>
    <style>
        :root {
            font-family: Arial, Helvetica, sans-serif;
            --ink: #202124;
            --muted: #5f6368;
            --line: #dadce0;
            --surface: #ffffff;
            --page: #f7f8fa;
            --brand: #146c5f;
            --brand-dark: #0f554b;
        }

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            color: var(--ink);
            background: var(--page);
        }

        main {
            width: min(820px, calc(100% - 32px));
            margin: 0 auto;
            padding: 32px 0 48px;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: end;
            gap: 16px;
            margin-bottom: 24px;
        }

        h1 {
            margin: 0;
            font-size: 32px;
            line-height: 1.15;
            letter-spacing: 0;
        }

        .muted {
            color: var(--muted);
        }

        form {
            background: var(--surface);
            border: 1px solid var(--line);
            border-radius: 8px;
            padding: 18px;
        }

        .grid {
            display: grid;
            grid-template-columns: repeat(2, minmax(0, 1fr));
            gap: 14px;
        }

        .full {
            grid-column: 1 / -1;
        }

        label {
            display: block;
            margin-bottom: 6px;
            color: var(--muted);
            font-size: 13px;
            font-weight: 700;
        }

        input,
        select,
        textarea {
            width: 100%;
            min-height: 42px;
            border: 1px solid #b8bec5;
            border-radius: 6px;
            padding: 9px 10px;
            font: inherit;
            background: #fff;
        }

        textarea {
            min-height: 110px;
            resize: vertical;
        }

        .actions {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
            margin-top: 18px;
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
        }

        .button:hover,
        button:hover {
            background: var(--brand-dark);
        }

        .button.secondary {
            color: var(--ink);
            background: #eceff1;
        }

        .button.secondary:hover {
            background: #dfe4e7;
        }

        @media (max-width: 680px) {
            header,
            .grid,
            .actions {
                display: grid;
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
<main>
    <header>
        <div>
            <h1>${formTitle}</h1>
            <p class="muted">${seller.businessName!"Seller"} · ID ${seller.userId}</p>
        </div>
        <a class="button secondary" href="/seller/listings?sellerId=${sellerId}">Back</a>
    </header>

    <form method="post" action="${formAction}">
        <div class="grid">
            <div class="full">
                <label for="title">Title</label>
                <input id="title" name="title" value="${listing.title!}" required>
            </div>

            <div class="full">
                <label for="description">Description</label>
                <textarea id="description" name="description">${listing.description!}</textarea>
            </div>

            <div>
                <label for="category">Category</label>
                <select id="category" name="category" required>
                    <#list categories as category>
                        <option value="${category}" <#if listing.category?? && listing.category == category>selected</#if>>${category}</option>
                    </#list>
                </select>
            </div>

            <div>
                <label for="itemCondition">Condition</label>
                <select id="itemCondition" name="itemCondition" required>
                    <#list conditions as condition>
                        <option value="${condition}" <#if listing.itemCondition?? && listing.itemCondition == condition>selected</#if>>${condition}</option>
                    </#list>
                </select>
            </div>

            <div>
                <label for="price">Price</label>
                <input id="price" name="price" type="number" min="0" step="0.01" value="${listing.price!}" required>
            </div>

            <div>
                <label for="quantity">Quantity</label>
                <input id="quantity" name="quantity" type="number" min="0" step="1" value="${listing.quantity!1}" required>
            </div>

            <div>
                <label for="status">Status</label>
                <select id="status" name="status" required>
                    <#list statuses as status>
                        <option value="${status}" <#if (listing.status?? && listing.status == status) || (!listing.status?? && status == "AVAILABLE")>selected</#if>>${status}</option>
                    </#list>
                </select>
            </div>
        </div>

        <div class="actions">
            <a class="button secondary" href="/seller/listings?sellerId=${sellerId}">Cancel</a>
            <button type="submit">Save Listing</button>
        </div>
    </form>
</main>
</body>
</html>
