# 🛍️ ShopEasy — Full-Stack E-Commerce Platform

> A modern full-stack online shopping application built with **Java, Spring Boot, Spring Data JPA, Hibernate, MySQL, HTML5, CSS3 and JavaScript**.

ShopEasy is a complete e-commerce project that demonstrates the end-to-end flow of an online shopping platform — from **user registration and product discovery to cart management, checkout, order placement, payment records, reviews and administration**.

---

## 🌟 Project Overview

**ShopEasy** is designed as a full-stack e-commerce application with separate customer and admin functionality.

### 👤 Customer Features

- 🔐 User registration and login
- 👤 Profile management
- 📱 Phone-based login flow
- 🛍️ Browse products
- 🔎 Search products
- 🗂️ Browse products by category
- 📄 View product details
- 🛒 Add products to cart
- ➕ Update cart item quantity
- ➖ Remove cart items
- 📦 Place orders
- 🏠 Manage delivery addresses
- 💳 Select payment method
- ⭐ Submit product reviews/ratings
- 📋 View order information

### 👨‍💼 Admin Features

- 🔐 Admin authentication
- 📊 Admin dashboard
- 📦 Product management
- 🗂️ Category management
- 👥 Customer management
- 🛒 Order management
- 🔎 Search and manage application data
- 🗑️ Product soft-delete/deactivation support

---

# 🚀 Live Deployment

> Replace the placeholders below with your actual deployed URLs.

| Application | Deployment URL |
|---|---|
| 🌐 Frontend | `https://shopeasyecommercewebsite.netlify.app` |
| ⚙️ Backend REST API | `https://shopeasy-e-commerce-2.onrender.com` |
| 🗄️ Database | Aiven MySQL |

### 🔗 API Base URL

```text
https://YOUR-BACKEND-URL/api
```

> ⚠️ Do not commit database passwords, API secrets or other credentials to GitHub.

---

# 🏗️ System Architecture

```text
                    ┌──────────────────────┐
                    │      👤 Customer     │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │   🌐 Frontend UI     │
                    │ HTML + CSS + JS       │
                    └──────────┬───────────┘
                               │
                         Fetch API / HTTP
                               │
                               ▼
                    ┌──────────────────────┐
                    │ ⚙️ Spring Boot REST  │
                    │      Controllers     │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │   🧠 Service Layer   │
                    │ Business Logic       │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │ 🗃️ Repository Layer  │
                    │ Spring Data JPA      │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │     🐬 MySQL         │
                    │   Aiven / Local DB   │
                    └──────────────────────┘
```

---

# 🧰 Technologies Used

## 🎨 Frontend

- HTML5
- CSS3
- JavaScript
- Fetch API
- Responsive UI
- CSS animations and transitions

## ⚙️ Backend

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate ORM
- RESTful APIs
- Maven
- Apache Tomcat

## 🗄️ Database

- MySQL
- Aiven MySQL for cloud deployment
- JDBC
- MySQL Connector/J

## ☁️ Deployment

- Frontend deployment
- Backend deployment on Render
- Cloud database using Aiven MySQL
- GitHub repository for source-code management

## 🛠️ Development Tools

- IntelliJ IDEA / VS Code
- Git
- GitHub
- Postman
- MySQL
- Maven
- Browser Developer Tools

---

# 📁 Project Structure

```text
ShopEasy/
│
├── frontend/
│   ├── HTML/
│   │   ├── index.html
│   │   ├── login.html
│   │   ├── register.html
│   │   ├── products.html
│   │   ├── product-details.html
│   │   ├── cart.html
│   │   ├── checkout.html
│   │   ├── customer-dashboard.html
│   │   └── ...
│   │
│   ├── CSS/
│   │   └── style.css
│   │
│   └── JS/
│       ├── login.js
│       ├── register.js
│       ├── products.js
│       ├── cart.js
│       ├── checkout.js
│       └── ...
│
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── .../shopeasy/
│   │   │   │       ├── controller/
│   │   │   │       ├── service/
│   │   │   │       ├── repository/
│   │   │   │       ├── entity/
│   │   │   │       ├── dto/
│   │   │   │       ├── exception/
│   │   │   │       └── config/
│   │   │   │
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   │
│   │   └── test/
│   │
│   └── pom.xml
│
└── README.md
```

> Folder names can be adjusted to match the exact folders in the repository.

---

# 🧩 Backend Architecture

The backend follows a layered architecture.

```text
Controller
    ↓
Service Interface
    ↓
Service Implementation
    ↓
Repository
    ↓
Entity
    ↓
MySQL Database
```

### 🎮 Controller Layer

Handles HTTP requests and exposes REST endpoints.

Responsibilities:

- Receive requests
- Validate request data
- Call service methods
- Return API responses

### 🧠 Service Layer

Contains the application's business logic.

Examples:

- Authentication
- Product operations
- Cart calculations
- Order processing
- Payment creation
- Review validation
- Address management

### 🗃️ Repository Layer

Uses Spring Data JPA to communicate with MySQL.

Repositories provide database operations without writing repetitive JDBC code.

### 🧱 Entity Layer

Represents database tables using JPA/Hibernate annotations.

### 📦 DTO Layer

DTOs are used for transferring request/response data between frontend and backend.

Examples:

- Register request
- Login request
- Create order request
- Cart response
- Product request/response

---

# 🗄️ Database Design

ShopEasy uses a relational MySQL database.

## Main Entities

```text
User
 ├── Address
 ├── Cart
 │    └── CartItem
 ├── Order
 │    └── Payment
 └── Review

Product
 ├── Category
 ├── CartItem
 ├── Order Item / Order relationship
 └── Review
```

---

## 👤 User Entity

Stores customer/admin account information.

Typical responsibilities:

- User identity
- Name
- Phone number
- Email
- Password
- Role
- Account information

### Relationship

```text
User 1 ──────── * Address
User 1 ──────── 1 Cart
User 1 ──────── * Order
User 1 ──────── * Review
```

---

## 🏠 Address Entity

Stores delivery address information.

Used during checkout and order placement.

```text
User
  │
  └────── Address
```

---

## 🛍️ Product Entity

Represents products available in the store.

Typical information includes:

- Product ID
- Product name
- Description
- Price
- Stock
- Image URL
- Category
- Active status

The active status supports soft-delete/deactivation instead of physically removing the product from the database.

---

## 🗂️ Category Entity

Groups products into categories.

Examples:

- 📱 Electronics
- 👕 Fashion
- 🏏 Sports
- 💊 Medicines
- 🏠 Household
- 🧸 Toys
- 👓 Accessories

Relationship:

```text
Category 1 ──────── * Product
```

---

## 🛒 Cart Entity

Represents the shopping cart belonging to a user.

```text
User 1 ──────── 1 Cart
```

---

## 🛒 CartItem Entity

Represents individual products inside a cart.

```text
Cart 1 ──────── * CartItem
Product 1 ────── * CartItem
```

A cart item connects a cart with a product and stores quantity information.

---

## 📦 Order Entity

Stores completed/placed order information.

Important information can include:

- Order ID
- User
- Delivery address
- Total amount
- Order status
- Order date
- Payment information

Relationship:

```text
User 1 ──────── * Order
```

---

## 💳 Payment Entity

Stores payment-related information associated with an order.

The project follows an order-driven payment flow where payment creation is handled as part of the order placement process rather than requiring a separate frontend payment request.

```text
Order 1 ──────── 1 Payment
```

---

## ⭐ Review Entity

Stores customer product reviews and ratings.

The application prevents duplicate reviews for the same user/product combination.

```text
User 1 ──────── * Review
Product 1 ───── * Review
```

---

# 🔄 Complete Customer Flow

```text
Register
   ↓
Login
   ↓
Customer Dashboard
   ↓
Browse Products
   ↓
Search / Category Filter
   ↓
Product Details
   ↓
Add to Cart
   ↓
View Cart
   ↓
Manage Quantity
   ↓
Checkout
   ↓
Select / Add Address
   ↓
Select Payment Method
   ↓
Place Order
   ↓
Create Order
   ↓
Create Payment Record
   ↓
Order Confirmation
```

---

# 🔐 Authentication Flow

```text
User
 ↓
Registration Form
 ↓
Frontend Validation
 ↓
POST /api/auth/register
 ↓
AuthController
 ↓
AuthService
 ↓
UserRepository
 ↓
MySQL
```

Login follows the same layered architecture:

```text
Login Form
 ↓
Fetch API
 ↓
Auth REST API
 ↓
Authentication Service
 ↓
User Repository
 ↓
Database
 ↓
Login Response
```

---

# 🛒 Cart Flow

```text
Product
   ↓
Add to Cart
   ↓
Cart API
   ↓
CartService
   ↓
CartRepository / CartItemRepository
   ↓
MySQL
```

Supported operations include:

- Add item
- View cart
- Update quantity
- Remove item
- Calculate cart totals

---

# 📦 Order & Checkout Flow

Checkout combines multiple parts of the system.

```text
Cart
 ↓
Checkout
 ↓
Select Address
 ↓
Select Payment Method
 ↓
CreateOrderRequest
 ↓
OrderService
 ↓
Validate User
 ↓
Validate Address
 ↓
Validate Cart
 ↓
Calculate Total
 ↓
Create Order
 ↓
Create Payment
 ↓
Persist Data
 ↓
Return Order Response
```

This keeps the order/payment process centralized in the backend.

---

# 💳 Payment Architecture

ShopEasy maintains a dedicated `Payment` entity.

The current architecture is designed around creating the payment record during order placement.

```text
Frontend
   │
   │ Place Order
   ▼
Order API
   │
   ▼
OrderService
   │
   ├── Create Order
   │
   └── Create Payment
           │
           ▼
       Payment Table
```

This avoids unnecessarily calling a separate payment-create endpoint from the checkout frontend when payment creation already belongs to the order transaction.

---

# ⭐ Review Flow

```text
Customer
   ↓
Product Details
   ↓
Submit Rating / Review
   ↓
Review API
   ↓
ReviewService
   ↓
Validate User + Product
   ↓
Check Existing Review
   ↓
Save Review
   ↓
MySQL
```

A unique user/product combination prevents duplicate reviews.

---

# 👨‍💼 Admin Architecture

The admin module provides management functionality over the application's core resources.

```text
Admin Login
    ↓
Admin Dashboard
    ↓
 ┌──────────────┬──────────────┬──────────────┬──────────────┐
 │ Products     │ Categories   │ Customers    │ Orders       │
 └──────────────┴──────────────┴──────────────┴──────────────┘
```

### Product Management

- Add products
- View products
- Search products
- Update products
- Deactivate products

### Category Management

- Add category
- View categories
- Search categories
- Manage categories

### Customer Management

- View customers
- Search customers
- Manage customer information

### Order Management

- View orders
- Track order information
- Manage order status

---

# 🌐 REST API Design

The backend exposes REST APIs under:

```text
/api
```

Major API areas include:

```text
/api/auth
/api/users
/api/products
/api/categories
/api/cart
/api/orders
/api/payments
/api/reviews
/api/addresses
```

> Exact endpoints may vary depending on the controller implementation in the repository.

---

# 🔗 Frontend ↔ Backend Communication

The frontend communicates with the Spring Boot backend using JavaScript `fetch()` requests.

Example architecture:

```text
JavaScript
    │
    │ HTTP Request
    ▼
Spring Boot REST Controller
    │
    ▼
Service
    │
    ▼
Repository
    │
    ▼
MySQL
```

The frontend API base URL is configured centrally so the application can work with the deployed backend.

---

# 🗃️ Database Configuration

## Local MySQL

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shopeasy
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## Cloud Database

For deployment, the project can connect to the **Aiven MySQL** database using environment variables/secrets.

> 🔒 Never commit real production credentials to GitHub.

---

# ⚙️ Spring Boot Configuration

Important configuration areas include:

```properties
spring.application.name=shopeasy

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

The exact production configuration should be maintained through Render environment variables rather than hard-coded credentials.

---

# ☁️ Deployment Architecture

```text
                    🌍 Internet
                         │
          ┌──────────────┴──────────────┐
          │                             │
          ▼                             ▼
   🌐 Frontend                     ⚙️ Backend
   Deployed App                   Render
                                        │
                                        ▼
                                 🗄️ Aiven MySQL
```

### Deployment Components

- 🌐 Frontend — deployed web application
- ⚙️ Backend — Spring Boot application deployed on Render
- 🗄️ Database — Aiven MySQL
- 📦 Source code — GitHub

---

# 🔒 Environment Variables

Production credentials should be stored as environment variables.

Recommended variables:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

Never expose:

```text
❌ Database password
❌ API secret
❌ Private credentials
❌ Access tokens
```

---

# 🧪 Testing

The project can be tested using:

### Browser

For:

- UI
- Login/Register
- Product browsing
- Cart
- Checkout

### Postman

For testing:

- Authentication APIs
- Product APIs
- Category APIs
- Cart APIs
- Order APIs
- Payment APIs
- Review APIs
- Address APIs

### Database

Use MySQL/Aiven console to verify:

- Users
- Products
- Categories
- Cart
- Orders
- Payments
- Reviews
- Addresses

---

# 🛠️ Error Handling

The backend includes exception-handling logic for common application errors.

Examples:

- ❌ User not found
- ❌ Product not found
- ❌ Category not found
- ❌ Cart not found
- ❌ Address not found
- ❌ Payment not found
- ❌ Invalid request
- ❌ Duplicate review
- ❌ Product unavailable

The objective is to return meaningful API responses instead of allowing raw database/application exceptions to reach the frontend.

---

# 📊 Core Business Rules

### Product

- Only active products should be available for normal shopping.
- Product availability should be validated before adding to cart/order.

### Cart

- A user owns a shopping cart.
- Cart items reference products.
- Quantity affects the cart total.

### Order

- Order total is calculated from the cart/order data.
- A valid delivery address is required.
- A payment method is required.
- Payment creation is linked to order placement.

### Review

- A user/product combination should not have duplicate reviews.

### Category

- Category names should be handled consistently and duplicate category names should be prevented.

---

# 🎯 Key Project Highlights

✨ Full-stack architecture

✨ RESTful API design

✨ Layered Spring Boot architecture

✨ Spring Data JPA + Hibernate

✨ Relational database design

✨ Customer and Admin modules

✨ Authentication and authorization flow

✨ Product/category management

✨ Shopping cart

✨ Checkout

✨ Order management

✨ Payment records

✨ Address management

✨ Product reviews and ratings

✨ Cloud database deployment

✨ Backend cloud deployment

✨ Responsive frontend

---

# 🧠 What This Project Demonstrates

This project demonstrates practical knowledge of:

- Java programming
- Object-oriented programming
- Spring Boot
- REST API development
- Spring Data JPA
- Hibernate ORM
- Entity relationships
- DTO pattern
- Repository pattern
- Service layer architecture
- MVC-style backend organization
- MySQL database management
- Frontend-backend integration
- HTTP/REST communication
- CRUD operations
- Exception handling
- Git/GitHub
- Cloud deployment
- Environment-based configuration

---

# 📌 Future Enhancements

Possible improvements for future versions:

- 🔐 JWT-based authentication
- 🔑 Password hashing with BCrypt
- 🛡️ Spring Security role-based authorization
- 💳 Real payment gateway integration
- 📧 Email notifications
- 📦 Advanced order tracking
- ❤️ Wishlist
- 🔔 Notifications
- 🧾 Invoice generation
- 📈 Advanced admin analytics
- 🖼️ Cloud image storage
- 🔍 Advanced product filtering and sorting
- 📱 PWA/mobile application

---

# 🚀 How to Run Locally

## 1️⃣ Clone Repository

```bash
git clone https://github.com/pratikjadhav7/ShopEasy-E-Commerce.git
cd ShopEasy-E-Commerce
```

## 2️⃣ Configure MySQL

Create a database:

```sql
CREATE DATABASE shopeasy;
```

Then configure the database credentials in the backend environment/configuration.

## 3️⃣ Start Backend

From the backend directory:

```bash
mvn clean install
mvn spring-boot:run
```

Backend will normally start on:

```text
http://localhost:8080
```

API base:

```text
http://localhost:8080/api
```

## 4️⃣ Start Frontend

Open the frontend through a local server such as VS Code Live Server.

Then configure the frontend API base URL to point to:

```text
http://localhost:8080/api
```

---

# 🔀 Git Workflow

```text
Local Development
       ↓
Git Add
       ↓
Git Commit
       ↓
Git Push
       ↓
GitHub
       ↓
Render Deployment
```

---

# 📝 Project Information

| Information | Details |
|---|---|
| 🏷️ Project Name | ShopEasy |
| 💻 Project Type | Full-Stack E-Commerce |
| ☕ Backend | Java + Spring Boot |
| 🎨 Frontend | HTML + CSS + JavaScript |
| 🗄️ Database | MySQL |
| ☁️ Cloud Database | Aiven MySQL |
| 🚀 Backend Deployment | Render |
| 📦 Version Control | Git + GitHub |
| 🔌 API Style | REST API |
| 🧩 ORM | Hibernate |
| 🗃️ Persistence | Spring Data JPA |
| 📦 Build Tool | Maven |
| ☕ Java Version | Java 21 |

---

# 👨‍💻 Author

**Pratik Jadhav**

### 🔗 Project Repository

```text
https://github.com/pratikjadhav7/ShopEasy-E-Commerce
```

---

# ⭐ Support

If you find this project useful, consider giving the repository a ⭐ on GitHub.

---

## 📜 License

This project is created for educational, learning and portfolio purposes.
