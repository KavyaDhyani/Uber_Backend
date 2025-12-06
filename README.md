# 🚀 RideShare Backend

This is a mini Ride Sharing backend built with Spring Boot and MongoDB. It features JWT authentication, input validation, and a clean architecture with Request -> Service -> Repository layers.

## 1️⃣ Project Overview

The project implements a backend for a ride-sharing application where:
- **Users** can register, login, and request rides.
- **Drivers** can register, login, view pending requests, and accept rides.
- Both can complete rides.

## 2️⃣ Tech Stack

- **Spring Boot**: Backend framework
- **MongoDB**: NoSQL Database
- **JWT (JSON Web Token)**: Authentication & Authorization
- **Spring Security**: Security framework
- **Jakarta Validation**: Input validation

## 3️⃣ Setup & Installation

1.  **Clone the repository**
2.  **Configure MongoDB**: Ensure MongoDB is running locally on the default port (27017).
3.  **Configure Port**: The documentation assumes the server runs on port `8080`. You can set this in `src/main/resources/application.yaml`:
    ```yaml
    server:
      port: 8080
    ```
    *(If running on default 8080, replace 8080 with 8080 in the commands below)*
4.  **Run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

## 4️⃣ API Endpoints

### Authentication

| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/register` | Register a new user or driver | Public |
| `POST` | `/api/auth/login` | Login and receive JWT token | Public |

### User Operations

| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/rides` | Request a new ride | User |
| `GET` | `/api/v1/user/rides` | View ride history | User |

### Driver Operations

| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/v1/driver/rides/requests` | View pending ride requests | Driver |
| `POST` | `/api/v1/driver/rides/{id}/accept` | Accept a ride request | Driver |

### Common Operations

| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/v1/rides/{id}/complete` | Mark a ride as completed | User/Driver |

## 5️⃣ CURL Commands for Testing

### 1. Register User
```bash
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{"username":"john","password":"1234","role":"ROLE_USER"}'
```

### 2. Register Driver
```bash
curl -X POST http://localhost:8080/api/auth/register \
-H "Content-Type: application/json" \
-d '{"username":"driver1","password":"abcd","role":"ROLE_DRIVER"}'
```

### 3. Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
-H "Content-Type: application/json" \
-d '{"username":"john","password":"1234"}'
```
*Response will contain the JWT token. Use this token in the Authorization header for subsequent requests.*

### 4. Create Ride (User)
```bash
curl -X POST http://localhost:8080/api/v1/rides \
-H "Authorization: Bearer <YOUR_TOKEN_HERE>" \
-H "Content-Type: application/json" \
-d '{"pickupLocation":"Koramangala","dropLocation":"Indiranagar"}'
```

### 5. View Pending Requests (Driver)
```bash
curl -X GET http://localhost:8080/api/v1/driver/rides/requests \
-H "Authorization: Bearer <DRIVER_TOKEN_HERE>"
```

### 6. Accept Ride (Driver)
```bash
curl -X POST http://localhost:8080/api/v1/driver/rides/{rideId}/accept \
-H "Authorization: Bearer <DRIVER_TOKEN_HERE>"
```

### 7. Complete Ride
```bash
curl -X POST http://localhost:8080/api/v1/rides/{rideId}/complete \
-H "Authorization: Bearer <TOKEN_HERE>"
```

## 6️⃣ Folder Structure

```
src/
 ├── main/
 │    ├── java/
 │    │     └── com/kavya_dhyani/uber/
 │    │           ├── model/
 │    │           ├── repository/
 │    │           ├── service/
 │    │           ├── controller/
 │    │           ├── config/
 │    │           ├── dto/
 │    │           ├── exception/
 │    │           └── util/
 │    └── resources/
 │            └── application.yaml
```
