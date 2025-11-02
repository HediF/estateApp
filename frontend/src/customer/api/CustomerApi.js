import AxiosInterceptor from '../../authentication/AxiosInterceptor';
import { API_ENDPOINTS } from '../../shared/AppConstants';

export const getCustomerProperties = async () => {
  const response = await AxiosInterceptor.get(
    API_ENDPOINTS.GET_CUSTOMER_PROPERTIES
  );
  return response.data;
};
