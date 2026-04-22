
# Requirements

**Project Name:** MinervasList\ Spartan MarketPlace
**Team:** Alex Hubbard(Customer) \Adrian Escobar (Provider)
**Course:** CSC 340\
**Version:** 1.0\
**Date:** 2026-01-30

---

## 1. Overview
**Vision.** Minervas List is a marketplace app for Spartans to connect and sell items or services. The provides one place for the marketing and listing of items.

**Glossary** Terms used in the project
- **Term 1:** description.
- **Term 2:** description

**Primary Users / Roles.**
- **Customer (e.g., Student/Patient/Pet Owner/etc. )** — Search, buy, and review products.
- **Provider (e.g., Teacher/Doctor/Pet Sitter/etc. )** — List and sell items and view statistics.

**Scope (this semester).**
- Account Login
- Product Listing
- Rating System
- Searching/Filtering
- Image Uploading
- Messaging

**Out of scope (deferred).**
- Personalized Feed
- Other University Inclusion
- In-app Transactions

> This document is **requirements‑level** and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

---

## 2. Functional Requirements (User Stories)
Write each story as: **As a `<role>`, I want `<capability>`, so that `<benefit>`.** Each story includes at least one **Given/When/Then** scenario.

### 2.1 Customer Stories
- **US‑CUST‑001 — Search Available Listings**  
  _Story:_ As a customer, I want to filter and search through listings so that I don't have to look through listings of things I don't want  
  _Acceptance:_
  ```gherkin
  Scenario: Customer succesfully searches for listing
    Given the customer is logged in and authenticated with the university
    When  the customer types in the product or selects a catergory for something they are looking for
    Then  all items related to the keyword or category show up and the customers search time is considerably shortened
  ```

- **US‑CUST‑002 — Customer-Seller Messaging**  
  _Story:_ As a customer, I want message sellers so that I know what I am buying or am able to haggle a deal  
  _Acceptance:_
  ```gherkin
  Scenario: Customer messages in regards to listing
    Given a user is authorized and in good standing and on a listing page
    When  there is vagueness in the descripiton so the customer presses the "Contact Seller" button
    Then  a messaging box pops up where the customer can contact the seller live
  ```

- **US‑CUST‑003 — Review Item**  
  _Story:_ As a customer, I want to leave reviews on products I bought so that future customers have an idea of what they are buying   
  _Acceptance:_
  ```gherkin
  Scenario: Customer recieves a product that exceeds their expectations
    Given a customer has confirmed purchase an item and they have not yet reviewed the item
    When  they return to the product page where the review button is active and they click it
    Then  the customer is able to write a review and leave a rating for the product, that they can post on the listing
  ```

- **US‑CUST‑004 — View Seller Profile**  
  _Story:_ As a customer, I want view a sellers profile so that I can see wht they specialize in or if they are good  
  _Acceptance:_
  ```gherkin
  Scenario: Customer is gauging seller quality
    Given the customer is logged in and on a listing screen
    When  the customer selects the sellers name or profile
    Then  the name is a links to the seller's profile which shows their rating, reviews people have left, and other listings they have up
  ```

### 2.2 Provider Stories
- **US‑PROV‑001 — Create Item Listing**  
  _Story:_ As a provider, I want to create a listing with photos, description, and price so that students can see and purchase my item.

  _Acceptance:_

  ```gherkin
  Scenario: Provider succesfully creates a listing
    Given the provider is logged into their verified student account
      And the provider is on the "Create Listing" page
    When the provider uploads atleast one photo, enters a title, a description and a price and clicks "Post" 
    Then the new listing appears in the browse feed, and the lisitng is visable on the providers profile.
  ```

- **US‑PROV‑002 — Manage My Listings**  
  _Story:_ As a provider, I want to edit or remove my listing so that my posted items stay accurate and up to date.

  _Acceptance:_

  ```gherkin
  Scenario: Provider edits an existing listing
    Given the provider is logged into their account 
      And the provider has at least one active listing
    When the provider selects a listing and updates the description or price and clicks "Save"
    Then the listing is updated in the marketplace feed and other users see the new information

  Scenario: Provider deletes a listing
    Given the provider is logged into their account and the provider has at least one active listing
    When the provider selects "Delete" on a listing and confirms the action
    Then the listing is removed from the marketplace feed and the item no longer appears on the provider’s profile
  ```
 
- **US‑PROV‑003 — Register and Manage Profile**  
  _Story:_ As a provider, I want to register with my university email and manage my profile so that I can create listings and be recognized as a verified student user.

  _Acceptance:_

  ```gherkin
  Scenario: Student successfully registers a verified account
    Given a user is on the Minerva's List registration page 
    When the user signs up using a valid @uncg.edu email address
    And completes the required profile information
    Then the system creates a verified student account 
    And the user can access both "Browse" and "Create" features
    ```


- **US‑PROV‑004 — Update Provider Profile Information**  
  _Story:_ As a provider, I want to edit my profile details so that my account information stays accurate.

  _Acceptance:_

  ```gherkin
  Scenario: Provider updates profile information
    Given the provider is logged into their account
    And the provider is on their profile settings page
    When the provider updates their display name, profile picture, or contact preferences and clicks "Save"
    Then the updated information appears on their public profile
  ```

- **US‑PROV‑005 — Reject Non-Student Email Registration**  
  _Story:_ As a provider, I want the system to restrict registration to university emails so that only verified students can create accounts. 

  _Acceptance:_

  ```gherkin
  Scenario: Non-student email is rejected
    Given a user is on the registration page
    When  the user attempts to register using an email that does not end in @uncg.edu
    Then  the system displays an error message
    And the account is not created
  ```
  
---

## 3. Non‑Functional Requirements (make them measurable)
- **Performance:** application should provide search results within 5 seconds 
- **Availability/Reliability:** services should be up 98% of the time
- **Security/Privacy:** all users should authenticate through a UNCG email
- **Usability:** this system shall be accessable by all students

---

## 4. Assumptions, Constraints, and Policies
- Must use a valid UNCG email (other Universities may be added later)
- Sellers must maintain 2.0 rating out of 5.0
- Customers may not leave reviews on products they have not bought, nor review unproportinally to times bought
- Chat messages must remain professional
- Assumes that eahc person uses their own account  

---

## 5. Milestones (course‑aligned)
- **M2 Requirements** — this file + stories opened as issues.
- **M3 High‑fidelity prototype** — core customer/provider flows fully interactive. 
- **M4 Design** — architecture, schema, API outline. 
- **M5 Backend API** — key endpoints + tests. 
- **M6 Increment** — ≥2 use cases end‑to‑end. 
- **M7 Final** — complete system & documentation. 

---

## 6. Change Management
- Stories are living artifacts; changes are tracked via repository issues and linked pull requests.  
- Major changes should update this SRS.
