# Hospital Management API

A Spring Boot RESTful API designed to manage hospital operations. The application handles associations between **Patients**, **Doctors**, **Departments**, **Insurance Policies**, and **Appointments**, secured with **Spring Security** and documented interactively via **Springdoc OpenAPI (Swagger UI)**.

## Technology Stack

- **Core Framework**: Spring Boot (v4.x / 3.x)
- **Data Access**: Spring Data JPA / Hibernate
- **Database**: PostgreSQL
- **Security**: Spring Security (HTTP Basic Auth)
- **API Documentation**: Springdoc OpenAPI / Swagger UI (v2.8.5)
- **Boilerplate Reduction**: Lombok

---

## Features

- **Patient Management**: Full CRUD operations, name updates, birthdate range searches, and blood group statistics.
- **Doctor Management**: Register doctors, update specializations/emails, and assign doctors to specific hospital departments.
- **Department Management**: Create departments, assign head doctors, and manage departments' medical staff.
- **Insurance Management**: Issue insurance policies to patients and validate policies dynamically.
- **Appointment Booking**: Schedule, reschedule, and reassign appointments between patients and doctors.

---

## Configuration & Credentials

### Database Configuration
Ensure a PostgreSQL database is running on `localhost:5432` with a database named `postgres`, or update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=password
```

### Security Accounts
All API endpoints (except Swagger UI documentation) are protected via HTTP Basic Authentication. Use one of the pre-configured in-memory accounts below to authorize your requests:

| Role  | Username | Password |
| :---- | :------- | :------- |
| **Admin** | `admin`   | `admin`   |
| **User**  | `user`    | `user`    |

---

## Running the Application

### 1. Build and Run
Use the Maven wrapper included in the project directory to compile and run:
```bash
./mvnw spring-boot:run
```

### 2. Run Tests
Verify the installation by running integration and repository tests:
```bash
./mvnw test
```

---

## API & Documentation Endpoints

Once the application is running, the interactive Swagger UI and OpenAPI documentation are accessible publicly:

- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI JSON Docs**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

> **Note**: Click the **Authorize** lock icon in Swagger UI and input the username/password (e.g., `admin` / `admin`) to call the API endpoints directly from the browser.

### Key API Mappings

| Resource | HTTP Method | Endpoint | Description |
| :--- | :--- | :--- | :--- |
| **Patients** | `POST` | `/api/patients` | Create patient record |
| | `GET` | `/api/patients/{id}` | Get patient by ID |
| | `GET` | `/api/patients/search?name={name}` | Find patient by name |
| | `GET` | `/api/patients/bloodgroup/count` | Get blood group statistics |
| **Doctors** | `POST` | `/api/doctors` | Register a doctor |
| | `POST` | `/api/doctors/{id}/departments/{deptId}` | Assign doctor to department |
| **Departments** | `POST` | `/api/departments` | Create department |
| | `PUT` | `/api/departments/{id}/head/{doctorId}` | Assign department head |
| **Insurance** | `POST` | `/api/insurances/assign?patientId={id}` | Assign policy to patient |
| **Appointments** | `POST` | `/api/appointments?doctorId={dId}&patientId={pId}` | Book a new appointment |
| | `PUT` | `/api/appointments/{id}/reassign/{dId}` | Reassign doctor for appointment |
