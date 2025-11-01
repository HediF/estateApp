import { API_ENDPOINTS } from '../../../shared/AppConstants';

export const loginUser = async ({ email, password }) => {
  const response = await fetch(`${API_ENDPOINTS.LOGIN}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ email, password }),
  });

  if (!response.ok) {
    const errorData = await response.json().catch(() => ({}));
    const message =
      errorData?.message || 'Login failed. Please check your credentials.';
    throw new Error(message);
  }

  return response.json();
};
