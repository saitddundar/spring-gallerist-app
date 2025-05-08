# Spring Gallerist App

The **Spring Gallerist App** is a robust and scalable backend web application designed for car dealership management. Built using **Java Spring Boot**, **Hibernate**, and **Maven**, it follows the **MVC (Model-View-Controller)** architectural pattern. The application prioritizes security, scalability, and clean code principles, making it suitable for both small dealerships and large enterprises.

## Key Features

### User Management
- **Registration**: Users can register via secure endpoints with password hashing using BCrypt.
- **Authentication & Authorization**: Utilizes **JWT (JSON Web Token)** for secure authentication and role-based access control.
- **Profile Management**: Users can update their profiles and manage notification preferences.

### Currency Conversion
- **Real-Time Conversion**: Integrated with the **Central Bank of the Republic of Turkey (TCMB) API** for up-to-date exchange rates.
- **Multi-Currency Support**: Facilitates vehicle sales in different currencies with accurate and flexible pricing options.

### Vehicle Management
- **Inventory Management**: RESTful APIs for adding, updating, and deleting vehicle listings.
- **Advanced Search**: Query-based endpoints for filtering vehicles based on price, make, model, and currency.

### Security
- **Role-Based Access Control**: Ensures users can only access resources they are authorized for.
- **Token-Based Sessions**: Secure session management with JWT.
- **Input Validation**: Protects against common vulnerabilities like SQL Injection.

### Event Management
- **Real-Time Updates**: Integration with WebSocket for real-time notifications and updates.
- **Event Scheduling**: Users can create and manage dealership events.

## Application Architecture

The project is structured following a layered architecture for maintainability and scalability:
1. **Controller Layer**: Handles HTTP requests and responses.
2. **Service Layer**: Contains business logic.
3. **Repository Layer**: Manages database interactions.
4. **Model Layer**: Defines data structures used across the application.

## Database
- **Relational Database**: Uses PostgreSQL for data storage.
- **JPA Entities**: Models are mapped to database tables using **JPA annotations**.
- **Custom Queries**: Implements complex queries using JPQL and native SQL.

## Setup and Installation

### Prerequisites
- **Java 11** or later
- **Maven** for dependency management
- **PostgreSQL Database**
- API Key for **Central Bank of the Republic of Turkey** (for currency conversion)

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/saitddundar/spring-gallerist-app.git
   cd spring-gallerist-app
   ```
2. Configure the `application.properties` file with your PostgreSQL credentials and TCMB API key.
3. Build the application:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
5. Access the application at `http://localhost:8080`.

## API Endpoints

### Authentication
- `POST /api/auth/register`: Register a new user.
- `POST /api/auth/login`: Authenticate and generate a JWT token.
- `POST /api/auth/logout`: Logout and invalidate the session.

### Vehicle Management
- `GET /api/vehicles`: Retrieve vehicle listings.
- `POST /api/vehicles`: Add a new vehicle.
- `PUT /api/vehicles/{id}`: Update an existing vehicle.
- `DELETE /api/vehicles/{id}`: Delete a vehicle.

### Currency Conversion
- `GET /api/currency/{currencyCode}`: Fetch real-time exchange rates.

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request for review.
