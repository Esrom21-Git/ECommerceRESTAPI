![Alt text](https://example.com/image.png)
![image](https://github.com/user-attachments/assets/1fdf547d-8d3b-41d4-be33-cdf0fc900879)

# 🛒 E-Commerce REST API

A comprehensive Spring Boot REST API for e-commerce applications with full CRUD operations, user authentication, and shopping cart functionality.

## 🚀 Features

### 🏷️ **Categories Management**
- View all product categories
- Get category by ID
- Create new categories (Admin only)
- Update existing categories (Admin only)
- Delete categories (Admin only)
- Get all products in a specific category

### 📦 **Products Management**
- Advanced product search with filters (category, price range, color)
- Get product by ID
- Create new products (Admin only)
- Update existing products (Admin only)
- Delete products (Admin only)
- List products by category

### 🛍️ **Shopping Cart**
- View user's shopping cart
- Add products to cart
- Update product quantities in cart
- Remove specific products from cart
- Clear entire cart
- Secure user-specific cart operations

### 🔐 **Security Features**
- Role-based access control (USER/ADMIN)
- JWT authentication integration
- Protected admin endpoints
- User-specific shopping cart access

## 🛠️ Technology Stack

- **Framework**: Spring Boot
- **Security**: Spring Security with JWT
- **Database**: MySQL with JDBC
- **Architecture**: REST API with DAO Pattern
- **Build Tool**: Maven
- **Java Version**: 11+

## 📁 Project Structure

```
src/main/java/org/yearup/
├── controllers/
│   ├── CategoriesController.java
│   ├── ProductsController.java
│   └── ShoppingCartController.java
├── data/
│   ├── CategoryDao.java
│   ├── ProductDao.java
│   ├── ShoppingCartDao.java
│   ├── UserDao.java
│   └── mysql/
│       ├── MySqlCategoryDao.java
│       ├── MySqlProductDao.java
│       └── MySqlDaoBase.java
└── models/
    ├── Category.java
    ├── Product.java
    ├── ShoppingCart.java
    ├── ShoppingCartItem.java
    └── User.java
```

## 🔗 API Endpoints

### Categories API
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/categories` | Get all categories | No |
| GET | `/categories/{id}` | Get category by ID | No |
| GET | `/categories/{id}/products` | Get products in category | No |
| POST | `/categories` | Create new category | Admin |
| PUT | `/categories/{id}` | Update category | Admin |
| DELETE | `/categories/{id}` | Delete category | Admin |

### Products API
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/products` | Search products with filters | No |
| GET | `/products/{id}` | Get product by ID | No |
| POST | `/products` | Create new product | Admin |
| PUT | `/products/{id}` | Update product | Admin |
| DELETE | `/products/{id}` | Delete product | Admin |

### Shopping Cart API
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/cart` | Get user's cart | User |
| POST | `/cart/products/{id}` | Add product to cart | User |
| PUT | `/cart/products/{id}` | Update product quantity | User |
| DELETE | `/cart/products/{id}` | Remove product from cart | User |
| DELETE | `/cart` | Clear entire cart | User |

## 🔍 Product Search Parameters

The products endpoint supports advanced filtering:

```http
GET /products?cat=1&minPrice=10.00&maxPrice=50.00&color=Red
```

**Parameters:**
- `cat` - Category ID filter
- `minPrice` - Minimum price filter
- `maxPrice` - Maximum price filter
- `color` - Color filter

## 📝 Request/Response Examples

### Create Category
```http
POST /categories
Content-Type: application/json

{
  "name": "Electronics",
  "description": "Electronic devices and accessories"
}
```

### Add Product to Cart
```http
POST /cart/products/15
Authorization: Bearer {jwt-token}
```

### Update Cart Item Quantity
```http
PUT /cart/products/15
Content-Type: application/json
Authorization: Bearer {jwt-token}

{
  "quantity": 3
}
```

## 🗄️ Database Schema

### Categories Table
```sql
CREATE TABLE categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT
);
```

### Products Table
```sql
CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    category_id INT,
    description TEXT,
    color VARCHAR(50),
    image_url VARCHAR(255),
    stock INT DEFAULT 0,
    featured BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);
```

## ⚙️ Configuration

### Database Configuration
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### Security Configuration
Ensure JWT security is properly configured for protected endpoints.

## 🚦 Getting Started

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   ```

2. **Configure database**
   - Create MySQL database
   - Update `application.properties` with your database credentials

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the API**
   - Base URL: `http://localhost:8080`
   - Test endpoints using Postman or curl

## 🔒 Authentication

- **Public endpoints**: Categories and Products (GET operations)
- **User endpoints**: Shopping cart operations
- **Admin endpoints**: Create, Update, Delete operations for categories and products

Use JWT tokens in the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

## 📊 Key Features Implemented

### ✅ Controllers
- **CategoriesController**: Complete CRUD operations with security
- **ProductsController**: Advanced search and CRUD operations
- **ShoppingCartController**: Full cart management functionality

### ✅ Data Access Layer
- **MySqlCategoryDao**: Optimized SQL queries for category operations
- **MySqlProductDao**: Dynamic search queries with proper filtering
- **Custom DAO pattern**: Clean separation of concerns

### ✅ Security & Error Handling
- Role-based access control
- Comprehensive error handling with proper HTTP status codes
- Input validation and null checks

## 🎯 Recent Improvements

1. **Fixed Product Search Logic**: Proper price range filtering and dynamic query building
2. **Enhanced Error Handling**: Better exception management with specific HTTP status codes
3. **Security Implementation**: Proper role-based access control
4. **Code Quality**: Clean, maintainable code with proper documentation
5. **Database Optimization**: Efficient SQL queries with parameterized statements



For support and questions, please open an issue in the GitHub repository.

---

**Built with ❤️ using Spring Boot and MySQL**
