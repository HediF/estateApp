# 🏠 Estate Management Platform – Frontend

This is the **frontend** for the Estate Management Platform, a full-stack application where **agents** can manage properties and customers, and **customers** can view their assigned properties.

Built with **ReactJS**, it communicates with the Spring Boot backend through REST APIs and provides an intuitive, modern UI for both agent and customer roles.

---

## 🚀 Features

### 👨‍💼 Agent

- Add new customers via a modal form
- Add new properties (title, address, price, new construction flag)
- Link a property to a customer through a dedicated modal
- View all managed properties and customers in dashboard sections

### 👤 Customer

- Log in and view properties assigned to their account (read-only access)

---

## 🧩 Tech Stack

- React 19
- React Testing Library + Jest for unit tests
- Axios for API communication
- React Context API for authentication
- Inline CSS styling
- React Router for navigation

---

## 🧱 Architecture Overview

    src/
     ├── agent/
     │    ├── api/                     → Agent-related API calls (add property, link property, etc.)
     │    ├── components/              → UI components specific to the Agent dashboard
     │    ├── AgentDashboard.jsx       → Main dashboard for agents
     │    └── AgentDashboard.test.jsx  → Unit & integration tests for agent flow
     │
     ├── authentication/
     │    ├── login/
     │    │    ├── api/                → Login API logic
     │    │    └── components/         → Login UI components
     │    ├── registration/
     │    │    ├── api/                → Registration API logic
     │    │    └── components/         → Registration UI components
     │    └── AxiosInterceptor.js      → Global Axios interceptor for token/error handling
     │
     ├── customer/
     │    ├── api/                     → Customer-related API calls
     │    ├── components/              → UI components for customers
     │    └── CustomerDashboard.jsx    → Customer dashboard (read-only property view)
     │
     ├── routing/
     │    ├── AgentRoutes.jsx          → Protected routes for agents
     │    ├── AuthRoutes.jsx           → Routes for authentication (login/register)
     │    ├── CustomerRoutes.jsx       → Protected routes for customers
     │    ├── ProtectedRoute.jsx       → HOC for guarding authenticated routes
     │    ├── PublicRoute.jsx          → Routes accessible without authentication
     │    └── RootRoutes.jsx           → Entry point for route composition
     │
     ├── shared/
     │    ├── components/              → Shared UI elements (modals, inputs, navbar)
     │    ├── context/                 → Global contexts (AuthContext, ErrorContext)
     │    ├── stores/                  → Shared state management (empty)
     │    └── AppConstants.js          → Global constants (roles, API URLs, etc.)
     │
     └── index.js / App.js           → Application entry point and root component

---

## ⚙️ Setup & Installation

### 1. Clone the repository

    git clone https://github.com/hedifeki/estate-frontend.git
    cd estate-frontend

### 2. Install dependencies

    npm install

### 3. Run the development server

    npm start
    # Frontend will run by default on http://localhost:3000

### 4. Environment variables

Create a `.env` file in the project root:

    REACT_APP_API_BASE_URL=http://localhost:8080/api

---

## 🧪 Testing

Run all Jest + React Testing Library tests:

    npm test

### Example test coverage

- AgentDashboard.test.jsx → verifies adding customers, properties, and linking
- AddCustomerModal.test.jsx → verifies input validation and submit state
- AddPropertyModal.test.jsx → verifies disabled/enabled behavior and form handling

---

## 🔐 Authentication Flow

1. Login → `/auth/login` returns a JWT access token.
2. Token is stored temporarily in localStorage (for now).
3. All API calls include `Authorization: Bearer <token>`.
4. Future improvement: replace localStorage with a secure mechanism (HttpOnly cookies or secure storage).

---

## ⚡ Improvements Roadmap

### Security

- Implement refresh token + silent renewal
- Move token from localStorage to a secure store  
  Example:
  import SecureStorage from "react-secure-storage";
  SecureStorage.setItem("accessToken", token);
  const token = SecureStorage.getItem("accessToken");

### UX / Validation

- Add frontend input validation for email and password strength
- Show clear error messages for invalid API responses
- Handle offline mode / API timeouts gracefully

### Internationalization

- Add i18n for English / German translations

### State Management

- Introduce Redux Toolkit when global state grows (e.g. caching users/properties across views)

### Testing

- Add integration tests for linking flows
- Simulate API errors in unit tests
- Add end-to-end coverage (Cypress)

---

## 🧠 Folder Structure (Simplified)

    src/
     ├── api/
     │    └── AgentApi.js
     ├── components/
     │    ├── AgentDashboard/
     │    ├── modals/
     │    ├── shared/
     │    └── ...
     ├── context/
     │    └── AuthContext.jsx
     ├── tests/
     ├── App.jsx
     └── index.jsx

---

## 🧰 Scripts

| Command       | Description                   |
| ------------- | ----------------------------- |
| npm start     | Run dev server                |
| npm run build | Build production bundle       |
| npm test      | Run all tests                 |
| npm run lint  | Check linting (if configured) |

---

## 👨‍💻 Author

**Hedi Feki**  
Founder & Full-Stack Developer  
📧 support@quickshift.team
