# 🏡 Real Estate Marketplace Platform

## 📌 Overview
This project is a full-stack real estate marketplace.

The platform enables **agents to manage properties and customers**, while **customers can securely access their assigned properties**. The system is built using a layered backend architecture and a modular frontend structure, with clear separation of concerns and centralized mechanisms such as authentication and error handling.

---

## 🧱 Tech Stack

### Backend
- Java, Spring Boot
- PostgreSQL
- OpenAPI (Swagger) for API contract generation
- JWT-based authentication
- Centralized error handling using Business Exceptions and Controller Advice

### Frontend
- ReactJS
- Axios (with interceptor pattern)
- JWT authentication
- Feature-based folder structure aligned with business domains:
  - `auth`
  - `agent`
  - `customer`
  - `shared`

---

## 🏗️ Architecture & Design Decisions

### Backend
The backend follows a **layered architecture**:

- **Controller Layer**  
  Handles HTTP requests and delegates to services. API contracts are defined via OpenAPI.

- **Service Layer**  
  Encapsulates business logic and enforces domain rules.

- **Repository Layer**  
  Handles persistence using JPA and PostgreSQL.

### Key Design Choices
- **Centralized Error Handling**  
  Implemented via custom Business Exceptions and `@ControllerAdvice` to ensure consistent error responses and separation from business logic.

- **DTO-driven API Design**  
  API contracts are clearly defined and decoupled from persistence models.

- **Stateless Authentication**  
  JWT-based authentication ensures scalability and avoids server-side session management.

---

### Frontend
The frontend is structured around **domain-driven modules** rather than technical layers.

- Each domain (`auth`, `agent`, `customer`) encapsulates:
  - Components
  - API calls
  - State logic

### Key Design Choices
- **Axios Interceptor Pattern**  
  Centralized handling of:
  - Authentication headers
  - Error responses
  - Token management

- **Separation of Concerns**  
  UI logic, API communication, and authentication are clearly decoupled.

---

## 👥 Domain Model

- **User (Base Entity)**
  - Common fields: `id`, `email`, `password`

- **Agent** extends User  
- **Customer** extends User  

- **Property**
  - Belongs to one Agent
  - Optionally assigned to one Customer

### Relationships
- One Agent → Many Properties  
- One Property → Zero or One Customer  

---

## 🔌 API Design

The API follows RESTful principles and is documented via OpenAPI.

### Core Endpoints

| Endpoint | Method | Description |
|--------|--------|------------|
| `/auth/register` | POST | Register agent |
| `/auth/login` | POST | Authenticate and return JWT |
| `/agent/customers` | GET / POST | Manage customers |
| `/agent/properties` | GET / POST | Manage properties |
| `/agent/properties/{id}/assign` | PUT | Assign property to customer |
| `/customer/properties` | GET | Retrieve assigned properties |

---

## 🔐 Authentication & Security

- JWT-based authentication
- Stateless backend design
- Token attached via Axios interceptor on the frontend
- Backend validation via authentication filter

### Design Consideration
JWT was chosen to:
- Avoid session state on the server
- Enable horizontal scalability
- Keep the API stateless

---

## ⚠️ Error Handling Strategy

### Backend
- Centralized using:
  - Custom Business Exceptions
  - `@ControllerAdvice`
- Ensures:
  - Consistent error structure
  - Clear separation of concerns
  - Maintainability

### Frontend
- Axios interceptor provides:
  - Global error handling
  - Authentication failure handling
  - Consistent UI behavior

---

## 🔄 Key User Flows

### Authentication
- User logs in → JWT issued by backend
- Token stored on frontend
- Token included in subsequent API calls

### Property Assignment
- Agent assigns property via API
- Backend updates relationship
- Frontend re-fetches and reflects updated state

---

## ▶️ Getting Started

### Backend

cd backend
./mvnw spring-boot:run

### Frontend

cd frontend  
npm install  
npm start  

### Database

- PostgreSQL required  
- Configure credentials in `application.properties`  

---

## 🧪 Testing Strategy (Planned)

Due to time constraints, testing was limited. A production-ready version would include:

### Backend
- Unit tests (JUnit, Mockito)  
- Integration tests (Spring Boot Test)  

### Frontend
- Unit and integration tests (Jest, React Testing Library)  

### End-to-End
- Cypress or Postman/Newman  

---

## 🚀 Future Improvements

### 🔐 Security
- Refresh token mechanism  
- Email verification or approval flow  

### ⚠️ Robustness
- Extended edge case handling  
- More validation annotations (`@NotNull`, `@NotBlank`, etc.)  
- Database constraints  
- Authorization checks with error responses  

### 🧪 Testing
- Full test coverage across all layers  
- End-to-end testing  

### 📘 Documentation
- Javadoc for backend  
- Extended API documentation  

### 🌍 Internationalization
- Multi-language support (DE / EN)  

### 🧱 DevOps
- Docker and docker-compose setup  
- CI/CD pipeline (e.g., GitHub Actions)  
- Environment-based configurations / Spring Profiles
