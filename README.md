# Role-Based Access Control (RBAC) Project
Overview
This project implements an Authentication, Authorization, and Role-Based Access Control (RBAC) system using Spring Boot and JWT. It demonstrates how to secure a system with role-based permissions, ensuring proper authentication and access control.
# Tech Stack
1) Spring Boot: Backend framework for building the application.
2) Spring Security: Provides authentication and authorization mechanisms.
3) JWT: Token-based authentication and session management.
4) MySQL Database: Database for development and testing.
5) Maven: Dependency management and build automation.

# Project Structure
src/main/java/com/rbac/
├── config/               # Security configurations
├── controller/           # REST controllers
├── entity/               # Entity classes for database
├── repository/           # Repository interfaces for database access
├── security/             # JWT utilities and filters
└── Application.java      # Main Spring Boot application class


## API Endpoints
Below is a summary of the API endpoints for the project:

| HTTP Method | Endpoint         | Description                 | Request Body                        | Response                    | Authorization Required |
|-------------|------------------|-----------------------------|-------------------------------------|-----------------------------|------------------------|
| POST        | /users/register  | Register a new user         | `{ "username": "...", "password": "..." }` | `{"message": "User registered successfully"}` | No                     |
| POST        | /users/login     | Authenticate and get a token| `{ "username": "...", "password": "..." }` | `{ "token": "JWT_TOKEN" }` | No                     |
| GET         | /users/all       | Get all users               | None                                | `[{"id":1,"username":"..." }]` | Yes                    |

# Sample Screenshots
![Screenshot (1)](https://github.com/user-attachments/assets/866f17f1-411f-4712-b0e7-46a508f00b4e)



