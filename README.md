# MediCore Hospital Management System (HMS)

MediCore HMS is a robust, production-ready Spring Boot 3 web application built with Java 17/21, Spring Data JPA, Security, and PostgreSQL. It has been fully refactored to enforce the **Data Transfer Object (DTO)** pattern, separating the database entity layer from the REST API endpoints to ensure security, validation, and zero recursion.

---

## 🏗️ Architecture & Data Flow

Below is the high-level architecture diagram showing how requests are processed through the layered architecture:

```mermaid
graph TD
    Client[REST Client / Swagger UI] <-->|JSON Request/Response DTOs| ControllerLayer[Controller Layer]
    ControllerLayer <-->|DTOs & Validation| ServiceLayer[Service Layer]
    ServiceLayer <-->|MapStruct Mappers| MapperLayer[Mapper Layer]
    ServiceLayer <-->|JPA Entities| RepositoryLayer[Spring Data JPA Repositories]
    RepositoryLayer <-->|SQL / JPQL| Database[(PostgreSQL Database)]

    subgraph DTO & Validation
        PatientRequestDTO
        PatientResponseDTO
        AppointmentRequestDTO
        AppointmentResponseDTO
    end

    subgraph Entity Mapping
        Patient[Patient Entity]
        Appointment[Appointment Entity]
    end
```

---

## 🛠️ Technology Stack

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23C5FA29?style=for-the-badge&logo=swagger&logoColor=black)
![Lombok](https://img.shields.io/badge/Lombok-%2392A8D1.svg?style=for-the-badge)
![MapStruct](https://img.shields.io/badge/MapStruct-%23F05032.svg?style=for-the-badge)

*   **Backend Framework:** Spring Boot 3.4.x (Java 17/21)
*   **Database Access:** Spring Data JPA / Hibernate (ORM)
*   **Database Engine:** PostgreSQL
*   **API Specification:** Springdoc OpenAPI / Swagger UI
*   **Object Mapping:** MapStruct (Compile-time code generation)
*   **Data Validation:** Jakarta Bean Validation (Hibernate Validator)
*   **Utility & Boilerplate:** Project Lombok


---

## 📂 Project Structure

```text
src/main/java/com/tarun/HospitalManagement/
│
├── config/                 # Security, CORS, and OpenAPI/Swagger Config
├── controller/             # REST Controllers (exposing only DTOs)
├── dto/                    # Data Transfer Objects
│   ├── request/            # Request payloads with Jakarta Validation
│   └── response/           # Response payloads and lightweight summaries
├── entity/                 # Database JPA Entities
├── exception/              # Global Exception Handler and custom exception mapping
├── mapper/                 # MapStruct Interfaces (Object-to-Object mappers)
├── repository/             # Spring Data JPA repositories
└── service/                # Business logic and Transaction boundaries
```

---

## 🚀 Key Features

*   **DTO Enforced API:** Controllers accept `@Valid` DTOs and return Response DTOs. Database entities (`Patient`, `Doctor`, etc.) never escape the Service Layer.
*   **Decoupled Relations:** Lightweight summaries (like `AppointmentSummaryDTO` and `InsuranceSummaryDTO`) are used in response objects to break cyclic JPA graphs and prevent `Infinite Recursion` / StackOverflow errors during JSON serialization.
*   **Global Exception Handling:** Centralized handling of validation errors (`400 Bad Request` with structured field errors) and resource lookups (`404 Not Found`).
*   **Auto-generated Mapping:** Stateless, compile-time safe object mappers using MapStruct.

---

## ⚙️ Project Setup & Installation

### Prerequisites
*   Java 17 or higher
*   Maven 3.8+
*   PostgreSQL running instance

### 1. Database Configuration
Update the `src/main/resources/application.properties` with your PostgreSQL database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/medicore_hms
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 2. Build the Project
Compile the project and trigger MapStruct code generation using the Maven Wrapper:
```bash
./mvnw clean compile
```

### 3. Run Unit Tests
Execute the test suites to ensure everything builds and mapping/security configurations function properly:
```bash
./mvnw test
```

### 4. Start the Application
Run the boot application:
```bash
./mvnw spring-boot:run
```

---

## 📖 API Documentation

Once the application is running, you can explore, test, and view schemas of all endpoints via Swagger UI:

*   **Swagger UI URL:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
*   **API Docs JSON:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
