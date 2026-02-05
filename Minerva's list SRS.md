
# Requirements – Starter Template

**Project Name:** MinervasList\ Spartan MarketPlace
**Team:** Alex Hubbard(Customer) \Adrian Escobar (Provider)
**Course:** CSC 340\
**Version:** 1.0\
**Date:** 2026-01-30

---

## 1. Overview
**Vision.** One or two sentences: who this is for, the core problem, and the outcome.

**Glossary** Terms used in the project
- **Term 1:** description.
- **Term 2:** description

**Primary Users / Roles.**
- **Customer (e.g., Student/Patient/Pet Owner/etc. )** — 1 line goal statement.
- **Provider (e.g., Teacher/Doctor/Pet Sitter/etc. )** — 1 line goal statement.

**Scope (this semester).**
- <capability 1>
- <capability 2>
- <capability 3>

**Out of scope (deferred).**
- <deferred 1>
- <deferred 2>

> This document is **requirements‑level** and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately.

---

## 2. Functional Requirements (User Stories)
Write each story as: **As a `<role>`, I want `<capability>`, so that `<benefit>`.** Each story includes at least one **Given/When/Then** scenario.

### 2.1 Customer Stories
- **US‑CUST‑001 — <short title>**  
  _Story:_ As a customer, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

- **US‑CUST‑002 — <short title>**  
  _Story:_ As a customer, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

### 2.2 Provider Stories
- **US‑PROV‑001 — <Create item listing>**  
  _Story:_ As a provider, I want to create a listing with photos, description, and price so that students can see and purchase my item.  
  _Acceptance:_
  ```gherkin
  Scenario: Provider succesfully creates a listing
    Given the provider is logged into their verified account student account and the provider is on the "create listing" tab
    When  the provider uploads atleast one photo, enters a title, a description and a price and clicks "post" 
    Then  the new listing appears in the browse feed, and the lisitng is visable through the providers profile.
  ```

- **US‑PROV‑002 — <Manage my listings >**  
  _Story:_ As a provider, I want to edit or remove my lisitng so that my posted items stay accurate and up to date 
  _Acceptance:_
  ```gherkin
  Scenario: Provider edits an existing listing
    Given the provider is logged into their account and the provider has at least one active listing
    When  the provider selects a listing and "updates" the description or price and clicks "Save"
    Then  the listing is updated in the marketplace feed and other users see the new information

  Scenario: Provider deletes a listing
    Given the provider is logged into their account and the provider has at least one active listing
    When the provider selects "Delete" on a listing and confirms the action
    Then the listing is removed from the marketplace feed and the item no longer appears on the provider’s profile
  ```
  - **US‑PROV‑003 — <Register and Manage Profile>**  
  _Story:_ As a provider, I want to register with my university email and manage my profile so that I can create listings and be recognized as a verified student user.
  _Acceptance:_
  ```gherkin
  Scenario: Student successfully registers a verified account
    Given a user is on the Minervas List registration page 
    When the user signs up using a valid @uncg.edu email address and completes the required profile information
    Then the system creates a verified student account and the user can access both "Browse" and "Create" features
    ```

- **US‑PROV‑004 — <sUpdate Provider Profile Information>**  
  _Story:_ As a provider, I want to edit my profile details so that my account information stays accurate.
  _Acceptance:_
  ```gherkin
  Scenario: Provider updates profile information
    Given the provider is logged into their account
    And the provider is on their profile settings page
    When the provider updates their display name, profile picture, or contact preferences and clicks "Save"
    Then the updated information appears on their public profile
  ```
- **US‑PROV‑005 — <Reject Non-Student Email Registration>**  
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
- **Performance:** description 
- **Availability/Reliability:** description
- **Security/Privacy:** description
- **Usability:** description

---

## 4. Assumptions, Constraints, and Policies
- list any rules, policies, assumptions, etc.

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
