import { API_ENDPOINTS } from '../../../shared/AppConstants';

export const registerAgent = async ({
  name,
  email,
  password,
  registrationCode,
}) => {
  const response = await fetch(`${API_ENDPOINTS.REGISTER_AGENT}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ name, email, password, registrationCode }),
  });

  if (!response.ok) {
    const errorText = await response.text();
    const message =
      response.status === 409
        ? 'Email already in use'
        : `Registration failed: ${errorText || response.status}`;
    throw new Error(message);
  }

  return response.json();
};
