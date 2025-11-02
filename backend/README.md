# 🏗️ Estate Management Platform – Backend

This is the Spring Boot backend for the Estate Management Platform. It provides REST APIs for agent and customer management, authentication, and property assignment.

---

## ⚙️ Tech Stack
- Spring Boot
- Spring Security
- PostgreSQL
- Lombok
- Maven
- Docker Compose (for local database)

---

## 🧩 Features
👨‍💼 Agent:
- Register with verification code and receive token
- Login to receive JWT token
- Create customers and properties
- Link properties to customers

👤 Customer:
- Login and view assigned properties (read-only)

---

## 🧱 Configuration (application.yml)
spring:
datasource:
url: jdbc:postgresql://localhost:5432/estate
username: ${DB_USERNAME}
password: ${DB_PASSWORD}
jpa:
hibernate:
ddl-auto: update

jwt:
secret: ${JWT_SECRET}
token-expiration: 3600 # 1 hour

registration:
agent-verification-code: ${AGENT_VERIFICATION_CODE}

Environment variables should be defined locally or in your container.

---

## 🐳 Docker Setup
version: '3.8'
services:
database:
image: postgres:latest
environment:
- POSTGRES_USER=${DB_USERNAME}
- POSTGRES_PASSWORD=${DB_PASSWORD}
- POSTGRES_DB=estate
ports:
- "5432:5432"

Run:
docker compose up -d

---

## 🚀 Running the Backend
1. Set environment variables:
   export DB_USERNAME=estate
   export DB_PASSWORD=yourPassword
   export JWT_SECRET=mySecretKey
   export AGENT_VERIFICATION_CODE=FINMAS

2. Run the app:
   mvn spring-boot:run

3. Backend will start on:
   http://localhost:8080

---

## 🔑 Authentication Flow
- Agents and customers authenticate via /auth/login
- JWT is returned as accessToken
- All subsequent API calls must include:
  Authorization: Bearer <token>
- Token validity: 1 hour
- Refresh token logic planned for next iteration.

---

## 🧠 Error Handling
Errors are localized and mapped via exceptions.properties

Examples:
- 00001: User already exists → Email already registered
- 00004: Invalid verification code → Incorrect or missing agent verification code
- 01002: Property not found → Property ID invalid or missing

---

## 🧰 Future Improvements
- Add refresh tokens for session renewal
- Expand input validation & error handling (API/DB)
- Add Dockerfile & CI/CD pipeline
- Add multi-language (EN/DE) exception translations

---

## 👨‍💻 Author
Hedi Feki