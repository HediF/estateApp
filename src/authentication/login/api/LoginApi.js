import { API_ENDPOINTS } from './../../../shared/AppConstants';
import AxiosInterceptor from './../../AxiosInterceptor';

export const loginUser = async ({ email, password }) => {
  const response = await AxiosInterceptor.post(API_ENDPOINTS.LOGIN, {
    email,
    password,
  });
  return response.data;
};
