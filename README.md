# Spring Gallerist App

Spring Gallerist App is a web application designed to simplify the management of artists and artworks for art galleries. The project enables you to list artworks, view detailed information about artists, and digitize gallery management processes.

## Features

- Create, update, and delete artist and artwork records
- Filter and search artworks by category
- RESTful API for integration
- Clean and user-friendly backend architecture

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database (for development/testing)
- Maven
- Swagger (API documentation)

## Installation & Usage

1. Clone the Project
   ```bash
   git clone https://github.com/saitddundar/spring-gallerist-app.git
   cd spring-gallerist-app
   ```

2. Install Dependencies
   ```bash
   mvn clean install
   ```

3. Start the Application
   ```bash
   mvn spring-boot:run
   ```

4. Access Swagger API Documentation
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

## Project Structure

```
spring-gallerist-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── pom.xml
└── README.md
```

## API Examples

- List Artists: `GET /api/artists`
- Add Artwork: `POST /api/works`
- Update Artwork: `PUT /api/works/{id}`
- Delete Artwork: `DELETE /api/works/{id}`

## Contributing

You can contribute by submitting a pull request or opening an issue. Please follow coding standards and provide clear explanations.

## License

This project is released under the MIT License.

---

**Contact:**  
For any questions or suggestions, [contact me via GitHub](https://github.com/saitddundar).
