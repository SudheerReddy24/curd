# CRUD Application – Spring Boot REST API

## 📌 Overview

This project is a backend REST API built using Spring Boot to perform CRUD (Create, Read, Update, Delete) operations on an entity.

The purpose of this project is to demonstrate clean backend development practices including layered architecture, DTO usage, validation, exception handling, and logging — instead of building a basic controller directly connected to the database.

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL / PostgreSQL
- Maven
- SLF4J (Logging)
- Swagger (OpenAPI)
- Git

---

## 🧠 Features

- RESTful API design
- Layered Architecture (Controller → Service → Repository)
- DTO pattern for request/response separation
- Entity-DTO mapping using ModelMapper
- Input validation using `@Valid`
- Global exception handling using `@ControllerAdvice`
- Structured error response format
- Logging using SLF4J
- API documentation with Swagger UI

---

## 📈 Architecture

This application follows a standard layered architecture:

Controller Layer  
Handles incoming HTTP requests and returns responses.

Service Layer  
Contains business logic and validation checks.

Repository Layer  
Handles database operations using Spring Data JPA.

DTO Layer  
Prevents exposing internal Entity objects directly to API consumers.

Exception Layer  
Provides centralized exception handling for clean and consistent error responses.

This structure improves maintainability, testability, and scalability.

---

## 🗄️ Database Structure

Example Entity Table:

- id (Primary Key)
- name
- email (Unique)
- created_at

You can modify this structure based on your entity design.

---

## ▶️ How to Run the Project

1. Clone the repository:
   ```
   git clone https://github.com/your-username/your-repository-name.git
   ```

2. Configure database in `application.properties`:
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Build the project:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

5. Access Swagger UI:
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## 🧪 API Endpoints

| Method | Endpoint        | Description        |
|--------|-----------------|--------------------|
| POST   | /crud/create   | Create new record  |
| GET    | /crud/retrieve   | Get all records    |
| GET    | /crud/getDataBtId/{id} | Get record by ID |
| PUT    | /crud/update/{id} | Update record   |
| DELETE | /crud/delete/{id} | Delete record   |

---

## 📌 Sample JSON Request

POST /api/entities

```
{
  "name": "John Doe",
  "email": "john@example.com"
}
```

---

## 🚀 What This Project Demonstrates

- Proper REST API structure
- Clean separation of concerns
- Production-style exception handling
- Validation and data integrity
- Logging for monitoring and debugging

---

## 👨‍💻 Author

Built for learning and demonstrating backend development using Spring Boot.
