# Minerva's List - MVC Application

A Spring MVC web application connecting UNCG students as buyers and sellers in a campus marketplace.

## Architecture Overview

This application follows the **Model-View-Controller (MVC)** pattern:

### Models (Entities)

Located in `src/main/java/com/CSC340/MinervasList/entity/`

- **User** - Base entity for authentication (parent class for Customer and Seller)
- **Customer** - Extends User; browses listings, makes purchases, and leaves reviews
- **Seller** - Extends User; manages listings and views sales activity
- **Listing** - Items posted for sale; includes title, description, price, quantity, category, condition, status, and optional photo
- **Purchase** - Records a customer's purchase of a listing with quantity, total price, and timestamp
- **Review** - Customer feedback on a seller with a star rating, comment, and optional seller reply

### Views (Templates)

Located in `src/main/resources/templates/`

**Shared Components:**

- `fragments/navbar.ftlh` - Reusable macro-based navigation bar (renders differently for customer vs. seller)

**Customer Views:**

- `customer/home.ftlh` - Customer landing page after login
- `customer/browse.ftlh` - Grid of all active listings with photo, title, price, category, and condition
- `customer/item-listing.ftlh` - Full listing detail page with purchase form and seller info card
- `customer/profile-page.ftlh` - Customer profile showing name and full purchase history

**Seller Views:**

- `seller/home.ftlh` - Seller dashboard
- `seller/profile.ftlh` - Seller public profile page
- `seller-listings.ftlh` - Manage all seller listings (view, edit, delete)
- `seller-listing-form.ftlh` - Create or edit a listing with photo upload

**Public Pages:**

- `home.ftlh` - App landing page with role-based navigation
- `login.ftlh` - Shared login page for customers and sellers
- `signup.ftlh` - Customer registration page

### Controllers

**API Controllers** – RESTful endpoints for data operations:

Located in `src/main/java/com/CSC340/MinervasList/controller/`

- `CustomerController` - Customer CRUD operations (`/customers`)
- `SellerController` - Seller profile operations (`/sellers`)
- `ListingController` - Listing CRUD and photo serving (`/listings`)
- `PurchaseController` - Purchase lifecycle (`/purchases`)
- `ReviewController` - Review submission, updates, deletion, and seller replies (`/reviews`)
- `UserController` - Shared user operations (`/users`)

**UI Controllers** – Page rendering and form handling:

Located in `src/main/java/com/CSC340/MinervasList/mvc/`

- `AppUiController` - Public pages (home, login)
- `CustomerUiController` - Customer dashboard, browse, shop, purchase, profile, signup, and logout (`/customer`)
- `SellerUiController` - Seller dashboard, listing management, and profile (`/seller`)

### Services

Located in `src/main/java/com/CSC340/MinervasList/service/`

Business logic layer providing CRUD operations and domain-specific functionality:

- `CustomerService` - Customer registration, lookup, update, and deletion
- `SellerService` - Seller registration, credential validation, user-type resolution, and profile photo management
- `ListingService` - Listing creation (with/without photo), update, seller ownership validation, and deletion (blocked if purchases exist)
- `PurchaseService` - Purchase creation, retrieval by customer or listing, update, and deletion
- `ReviewService` - Review creation linked to customer and seller, update, deletion, and seller reply workflow
- `UserService` - Shared user lookup (e.g., find by email)

### Repositories

Located in `src/main/java/com/CSC340/MinervasList/repository/`

Data access layer interfacing with the database via Spring Data JPA:

- `CustomerRepository` - Customer lookups and queries
- `SellerRepository` - Seller management and email-based lookup
- `ListingRepository` - Listing queries (by seller, by ID)
- `PurchaseRepository` - Purchase queries (by customer, by listing)
- `ReviewRepository` - Review queries (by customer, by seller, seller-scoped review lookup)
- `UserRepository` - Shared user queries (by email)

### DTOs

Located in `src/main/java/com/CSC340/MinervasList/dto/`

- `ReviewReplyRequest` - Payload for a seller replying to a review
- `SellerStatsDto` - Aggregated statistics for a seller's dashboard

---

## Key Features

### User Roles & Authentication

- **Customer** – Browse listings, purchase items, view purchase history, leave reviews on sellers
- **Seller** – Create and manage listings (with photo upload), view sales, reply to reviews, manage profile
- Login is shared; user type is resolved automatically and routes to the correct dashboard

### Customer Flow

1. Sign up and create a customer account (`/customer/signup`)
2. Log in and land on the customer home page (`/customer/home`)
3. Browse all active listings as a card grid (`/customer/browse`)
4. Click a listing to view full details — photo, price, quantity, condition, category, description, and seller info (`/customer/shop/{listingId}`)
5. Enter a quantity and purchase the item; purchase is recorded immediately (`POST /customer/shop/{listingId}/purchase`)
6. View purchase history on the profile page — title, quantity, total price, category, and date (`/customer/profile`)

### Seller Flow

1. Register and log in as a seller
2. Create listings with title, description, price, quantity, category, condition, and optional photo (`/seller/listings/new`)
3. Edit or delete existing listings from the listing management page (`/seller/listings`)
4. View profile and listing stats (`/seller/profile`)
5. Reply to customer reviews via the API (`PUT /reviews/{reviewId}/seller/{sellerId}/reply`)

### Navigation

All pages use a FreeMarker macro-based navbar (`fragments/navbar.ftlh`) that renders role-appropriate links for customers and sellers, built with Bootstrap 5.3.

---

## Session Management

- Uses `HttpSession` for storing `customerId` and `sellerId` after login
- All protected routes redirect unauthenticated users to `/login`
- Logout invalidates the session (`/customer/logout`, `/seller/logout`)

---

## Database Relationships

- **One-to-Many**: Seller → Listings, Customer → Purchases, Customer → Reviews, Seller → Reviews
- **Many-to-One**: Listing → Seller, Purchase → Customer, Purchase → Listing, Review → Customer, Review → Seller
- **Cascade**: Listings cannot be deleted if purchases exist (enforced in `ListingService`)
- **JsonIgnoreProperties**: Applied on Listing → Seller to prevent circular JSON serialization