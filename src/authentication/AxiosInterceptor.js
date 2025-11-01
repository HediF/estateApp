import axios from 'axios';
import { API_BASE_URL, STORAGE_KEYS } from '../shared/AppConstants';

const AxiosInterceptor = axios.create({
  baseURL: API_BASE_URL,
  headers: { 'Content-Type': 'application/json' },
});

AxiosInterceptor.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem(STORAGE_KEYS.ACCESS_TOKEN);
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

AxiosInterceptor.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      console.warn('[API] Unauthorized. Redirecting to login.');
      localStorage.removeItem(STORAGE_KEYS.ACCESS_TOKEN);
      window.location.href = '/auth/login';
    }
    return Promise.reject(error);
  }
);

export default AxiosInterceptor;
