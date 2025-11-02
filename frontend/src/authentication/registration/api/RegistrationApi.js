import AxiosInterceptor from '../../../authentication/AxiosInterceptor';
import { API_ENDPOINTS } from '../../../shared/AppConstants';

export const registerAgent = async ({
  name,
  email,
  password,
  registrationCode,
}) => {
  const response = await AxiosInterceptor.post(API_ENDPOINTS.REGISTER_AGENT, {
    name,
    email,
    password,
    registrationCode,
  });
  return response.data;
};
