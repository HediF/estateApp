import axios from 'axios';
import { API_BASE_URL, STORAGE_KEYS } from '../shared/AppConstants';
import { globalErrorHandler } from '../shared/components/error/globalErrorHandler';
import { handleApiError } from '../shared/components/error/handleApiError';

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
    const apiError = handleApiError(error);
    globalErrorHandler(apiError);
    if (error.response && [401, 403].includes(error.response.status)) {
      localStorage.removeItem(STORAGE_KEYS.ACCESS_TOKEN);
      window.location.href = '/login';
    }

    return Promise.resolve({ data: null });
  }
);

export default AxiosInterceptor;
