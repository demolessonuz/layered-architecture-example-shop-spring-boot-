# Spring Boot Shop Application

A modern e-commerce application built with Spring Boot, following a layered architecture pattern. This application provides RESTful APIs for managing products and orders in an online shop.

## Features

- Product Management (CRUD operations)
- Order Management (CRUD operations)
- Stock Management
- Customer Order Tracking
- Swagger API Documentation
- H2 In-Memory Database
- Global Exception Handling
- Input Validation
- Layered Architecture

## Architecture

The application follows a layered architecture pattern with the following components:

### 1. Presentation Layer (Controllers)
- `ProductController`: Handles HTTP requests for product-related operations
- `OrderController`: Manages order-related HTTP requests
- RESTful endpoints with proper HTTP methods and status codes
- Swagger documentation for API endpoints

### 2. Service Layer
- `ProductService`: Business logic for product operations
- `OrderService`: Business logic for order operations
- Transaction management
- Business rule validation

### 3. Repository Layer
- `ProductRepository`: Data access for products
- `OrderRepository`: Data access for orders
- JPA repositories for database operations

### 4. Domain Layer
- `Product`: Product entity with properties and validation
- `Order`: Order entity with relationships and validation
- `OrderItem`: Order item entity for order-product relationship

### 5. DTO Layer
- `ProductDTO`: Data transfer objects for products
- `OrderDTO`: Data transfer objects for orders
- Request/Response DTOs for API communication

### 6. Mapper Layer
- `ProductMapper`: Converts between Product entities and DTOs
- `OrderMapper`: Converts between Order entities and DTOs

## Technologies Used

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database
- Maven
- Swagger/OpenAPI
- Lombok
- Validation API

## Prerequisites

- JDK 17 or later
- Maven 3.6 or later
- Git (optional)

## Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd shop
```

2. Build the project:
```bash
./mvnw clean install
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8085`

## API Documentation

Once the application is running, you can access:
- Swagger UI: `http://localhost:8085/swagger-ui.html`
- OpenAPI Documentation: `http://localhost:8085/api-docs`

## Database Access

The application uses H2 in-memory database:
- H2 Console: `http://localhost:8085/h2-console`
- JDBC URL: `jdbc:h2:mem:shopdb`
- Username: `SA`
- Password: `` (empty)

## API Endpoints

### Products
- `GET /api/products`: Get all products
- `GET /api/products/{id}`: Get product by ID
- `POST /api/products`: Create new product
- `PUT /api/products/{id}`: Update product
- `DELETE /api/products/{id}`: Delete product
- `PATCH /api/products/{id}/stock`: Update product stock

### Orders
- `GET /api/orders`: Get all orders
- `GET /api/orders/{id}`: Get order by ID
- `POST /api/orders`: Create new order
- `PUT /api/orders/{id}`: Update order
- `DELETE /api/orders/{id}`: Delete order
- `PATCH /api/orders/{id}/status`: Update order status
- `GET /api/orders/customer/{email}`: Get orders by customer email

## Project Structure

```
src/main/java/com/shop/
├── ShopApplication.java
├── controller/
│   ├── ProductController.java
│   └── OrderController.java
├── service/
│   ├── ProductService.java
│   ├── ProductServiceImpl.java
│   ├── OrderService.java
│   └── OrderServiceImpl.java
├── repository/
│   ├── ProductRepository.java
│   └── OrderRepository.java
├── entity/
│   ├── Product.java
│   ├── Order.java
│   └── OrderItem.java
├── dto/
│   ├── ProductDTO.java
│   └── OrderDTO.java
├── mapper/
│   ├── ProductMapper.java
│   └── OrderMapper.java
├── exception/
│   ├── GlobalExceptionHandler.java
│   └── ErrorResponse.java
└── config/
    └── OpenApiConfig.java
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 