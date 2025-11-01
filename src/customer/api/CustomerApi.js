import AxiosInterceptor from '../../authentication/AxiosInterceptor';
import { API_ENDPOINTS } from '../../shared/AppConstants';

export const getCustomerProperties = async () => {
  try {
    const response = await AxiosInterceptor.get(
      API_ENDPOINTS.GET_CUSTOMER_PROPERTIES
    );
    return response.data;
  } catch (error) {
    console.error(
      '[API] Failed to fetch customer properties:',
      error.response || error
    );
    throw error.response?.data || error;
  }
};
