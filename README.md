# Spring Gallerist App

A modern and scalable RESTful API application built with Spring Boot for managing car gallery operations, including gallerist management, car inventory, customer records, and sales transactions.

## About the Project

Spring Gallerist App is a comprehensive backend system designed for car dealership management. The application provides a complete set of REST APIs for handling gallerists, cars, customers, accounts, addresses, and sales operations with JWT-based authentication.

## Features

- User authentication and authorization with JWT tokens
- Refresh token mechanism for secure session management
- Complete CRUD operations for gallerists, cars, customers, and accounts
- Address management system
- Car sales tracking and management
- Gallerist-Car relationship management
- Real-time currency rates integration
- Request/Response validation
- Centralized error handling
- RESTful API design principles

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Security with JWT
- Spring Data JPA
- Hibernate
- PostgreSQL / MySQL (production)
- H2 Database (development/testing)
- Maven
- Lombok
- Bean Validation (Jakarta Validation)

## Installation & Usage

1. Clone the Project
   ```bash
   git clone https://github.com/saitddundar/spring-gallerist-app.git
   cd spring-gallerist-app
   ```

2. Configure Database
   Update `application.properties` or `application.yml` with your database credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/gallerist_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. Install Dependencies
   ```bash
   mvn clean install
   ```

4. Start the Application
   ```bash
   mvn spring-boot:run
   ```

5. Access the API
   ```
   http://localhost:8080
   ```

## Project Structure

```
spring-gallerist-app/
├── gallerist/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/saitdundar/
│   │   │   │       ├── controller/
│   │   │   │       │   └── impl/
│   │   │   │       ├── dto/
│   │   │   │       ├── entity/
│   │   │   │       ├── repository/
│   │   │   │       └── service/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
└── README.md
```

## API Documentation

### Authentication Endpoints

#### Register New User
```http
POST /register
Content-Type: application/json

{
  "username": "string",
  "password": "string"
}
```

**Response:**
```json
{
  "status": "success",
  "data": {
    "id": "long",
    "username": "string",
    "createdAt": "timestamp"
  }
}
```

#### Authenticate User
```http
POST /authenticate
Content-Type: application/json

{
  "username": "string",
  "password": "string"
}
```

**Response:**
```json
{
  "status": "success",
  "data": {
    "accessToken": "string",
    "refreshToken": "string",
    "expiresIn": "long"
  }
}
```

#### Refresh Token
```http
POST /refreshToken
Content-Type: application/json

{
  "refreshToken": "string"
}
```

**Response:**
```json
{
  "status": "success",
  "data": {
    "accessToken": "string",
    "refreshToken": "string",
    "expiresIn": "long"
  }
}
```

### Gallerist Management

#### Create Gallerist
```http
POST /rest/api/gallerist/save
Authorization: Bearer {token}
Content-Type: application/json

{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "phone": "string",
  "addressId": "long"
}
```

**Response:**
```json
{
  "status": "success",
  "data": {
    "id": "long",
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "phone": "string",
    "address": {}
  }
}
```

### Car Management

#### Create Car
```http
POST /rest/api/car/save
Authorization: Bearer {token}
Content-Type: application/json

{
  "brand": "string",
  "model": "string",
  "year": "integer",
  "price": "decimal",
  "currency": "string",
  "color": "string",
  "kilometers": "integer"
}
```

**Response:**
```json
{
  "status": "success",
  "data": {
    "id": "long",
    "brand": "string",
    "model": "string",
    "year": "integer",
    "price": "decimal",
    "currency": "string",
    "color": "string",
    "kilometers": "integer"
  }
}
```

### Customer Management

#### Create Customer
```http
POST /rest/api/customer/save
Authorization: Bearer {token}
Content-Type: application/json

{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "phone": "string",
  "identityNumber": "string",
  "addressId": "long"
}
```

**Response:**
```json
{
  "status": "success",
  "data": {
    "id": "long",
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "phone": "string",
    "identityNumber": "string",
    "address": {}
  }
}
```

### Account Management

#### Create Account
```http
POST /rest/api/account/save
Authorization: Bearer {token}
Content-Type: application/json

{
  "accountNumber": "string",
  "iban": "string",
  "balance": "decimal",
  "currency": "string",
  "customerId": "long"
}
```

**Response:**
```json
{
  "status": "success",
  "data": {
    "id": "long",
    "accountNumber": "string",
    "iban": "string",
    "balance": "decimal",
    "currency": "string",
    "customer": {}
  }
}
```

### Address Management

#### Create Address
```http
POST /rest/api/address/save
Authorization: Bearer {token}
Content-Type: application/json

{
  "street": "string",
  "city": "string",
  "state": "string",
  "zipCode": "string",
  "country": "string"
}
```

**Response:**
```json
{
  "status": "success",
  "data": {
    "id": "long",
    "street": "string",
    "city": "string",
    "state": "string",
    "zipCode": "string",
    "country": "string"
  }
}
```

### Gallerist-Car Management

#### Assign Car to Gallerist
```http
POST /rest/api/gallerist-car/save
Authorization: Bearer {token}
Content-Type: application/json

{
  "galleristId": "long",
  "carId": "long"
}
```

**Response:**
```json
{
  "status": "success",
  "data": {
    "id": "long",
    "gallerist": {},
    "car": {}
  }
}
```

### Sales Management

#### Purchase Car
```http
POST /rest/api/saled-car/purchase
Authorization: Bearer {token}
Content-Type: application/json

{
  "carId": "long",
  "customerId": "long",
  "galleristId": "long",
  "salePrice": "decimal",
  "saleDate": "date"
}
```

**Response:**
```json
{
  "status": "success",
  "data": {
    "id": "long",
    "car": {},
    "customer": {},
    "gallerist": {},
    "salePrice": "decimal",
    "saleDate": "date"
  }
}
```

### Currency Rates

#### Get Currency Rates
```http
GET /rest/api/currency-rates?startDate={startDate}&endDate={endDate}
Authorization: Bearer {token}
```

**Parameters:**
- `startDate` (required): Start date in format YYYY-MM-DD
- `endDate` (required): End date in format YYYY-MM-DD

**Response:**
```json
{
  "status": "success",
  "data": {
    "rates": [],
    "startDate": "string",
    "endDate": "string"
  }
}
```

## Response Format

All API responses follow this standardized format:

**Success Response:**
```json
{
  "status": "success",
  "data": {}
}
```

**Error Response:**
```json
{
  "status": "error",
  "message": "string",
  "errors": []
}
```

## Authentication

The API uses JWT (JSON Web Tokens) for authentication. Include the token in the Authorization header:

```
Authorization: Bearer {your_jwt_token}
```

Tokens expire after a specified time. Use the refresh token endpoint to obtain a new access token.

## Validation

All input data is validated using Jakarta Bean Validation annotations. Invalid requests will return a 400 Bad Request with detailed error messages.

## Error Handling

The application implements centralized error handling for:
- Validation errors (400)
- Authentication errors (401)
- Authorization errors (403)
- Resource not found (404)
- Internal server errors (500)

## Security Features

- Password encryption using BCrypt
- JWT-based authentication
- Refresh token mechanism
- Role-based access control
- SQL injection prevention
- XSS protection

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

Please ensure your code follows the project's coding standards and includes appropriate tests.

## License

This project is released under the MIT License.

## Contact

For any questions or suggestions, [contact me via GitHub](https://github.com/saitddundar).

## Acknowledgments

- Spring Boot Team
- Spring Security Team
- All contributors to this project
