export const USER_TYPE = {
  AGENT: 'AGENT',
  CUSTOMER: 'CUSTOMER',
};

export const API_BASE_URL = 'http://localhost:8080';

export const API_ENDPOINTS = {
  REGISTER_AGENT: `${API_BASE_URL}/agent/register`,
  LOGIN: `${API_BASE_URL}/auth/login`,
  ADD_CUSTOMER: `${API_BASE_URL}/agent/customers`,
  GET_CUSTOMERS: `${API_BASE_URL}/agent/customers`,
  ADD_PROPERTY: `${API_BASE_URL}/agent/properties`,
  GET_PROPERTIES: `${API_BASE_URL}/agent/properties`,
  ASSIGN_PROPERTY: `${API_BASE_URL}/agent/properties`,
  GET_CUSTOMER_PROPERTIES: `${API_BASE_URL}/customer/properties`,
};

export const ROUTES = {
  AUTH_LOGIN: '/auth/login',
  AUTH_REGISTER: '/auth/register',
  AGENT_DASHBOARD: '/agent/dashboard',
  CUSTOMER_DASHBOARD: '/customer/dashboard',
};

export const STORAGE_KEYS = {
  ACCESS_TOKEN: 'accessToken',
  USER: 'user',
};
