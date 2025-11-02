import AxiosInterceptor from '../../authentication/AxiosInterceptor';
import { API_ENDPOINTS } from './../../shared/AppConstants';

export const addCustomer = async (customerData) => {
  const response = await AxiosInterceptor.post(
    API_ENDPOINTS.ADD_CUSTOMER,
    customerData
  );
  return response.data;
};

export const addProperty = async (propertyData) => {
  const response = await AxiosInterceptor.post(
    API_ENDPOINTS.ADD_PROPERTY,
    propertyData
  );
  return response.data;
};

export const getAllCustomers = async () => {
  const response = await AxiosInterceptor.get(API_ENDPOINTS.ADD_CUSTOMER);
  return response.data;
};

export const getAllProperties = async () => {
  const response = await AxiosInterceptor.get(API_ENDPOINTS.ADD_PROPERTY);
  return response.data;
};

export const assignPropertyToCustomer = async (propertyId, customerId) => {
  const response = await AxiosInterceptor.put(
    `${API_ENDPOINTS.ASSIGN_PROPERTY}/${propertyId}/assign`,
    { customerId }
  );
  return response.data;
};
