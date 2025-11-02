import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import AgentDashboard from './AgentDashboard';
import * as AgentApi from './api/AgentApi';
import userEvent from '@testing-library/user-event';

jest.mock('./api/AgentApi', () => ({
  addCustomer: jest.fn(),
  addProperty: jest.fn(),
  getAllCustomers: jest.fn(),
  getAllProperties: jest.fn(),
  assignPropertyToCustomer: jest.fn(),
}));

jest.mock('../shared/context/AuthContext', () => ({
  useAuth: () => ({
    user: { email: 'agent@test.com', name: 'Agent Smith' },
    logout: jest.fn(),
  }),
}));

describe('AgentDashboard', () => {
  beforeEach(() => {
    jest.clearAllMocks();
    AgentApi.getAllCustomers.mockResolvedValue([]);
    AgentApi.getAllProperties.mockResolvedValue([]);
  });

  test('shows empty states when there are no customers or properties', async () => {
    AgentApi.getAllCustomers.mockResolvedValue([]);
    AgentApi.getAllProperties.mockResolvedValue([]);

    render(<AgentDashboard />);

    await waitFor(() => {
      expect(AgentApi.getAllCustomers).toHaveBeenCalled();
      expect(AgentApi.getAllProperties).toHaveBeenCalled();
    });

    expect(screen.getByText('No customers found')).toBeInTheDocument();
    expect(screen.getByText('No properties found')).toBeInTheDocument();
  });

  test('renders dashboard and fetches initial data', async () => {
    render(<AgentDashboard />);

    await waitFor(() => {
      expect(AgentApi.getAllCustomers).toHaveBeenCalled();
      expect(AgentApi.getAllProperties).toHaveBeenCalled();
    });

    expect(screen.getByText('Agent Dashboard')).toBeInTheDocument();
    expect(screen.getByTestId('properties-section-title')).toBeInTheDocument();
    expect(screen.getByText('Customers')).toBeInTheDocument();
  });

  test('opens Add Customer modal, verifies disabled state, fills form, and submits new customer', async () => {
    AgentApi.addCustomer.mockResolvedValue({
      id: 1,
      name: 'John Doe',
      email: 'john@example.com',
    });

    render(<AgentDashboard />);

    const openModalBtn = screen.getByTestId('navbar-add-customer-btn');
    fireEvent.click(openModalBtn);

    const submitBtn = screen.getByTestId('add-customer-modal-submit');
    expect(submitBtn).toBeDisabled();

    await userEvent.type(
      screen.getByTestId('add-customer-input-name'),
      'John Doe'
    );
    await userEvent.type(
      screen.getByTestId('add-customer-input-email'),
      'john@example.com'
    );
    await userEvent.type(
      screen.getByTestId('add-customer-input-password'),
      'secret123'
    );

    expect(submitBtn).toBeEnabled();

    userEvent.click(submitBtn);

    await waitFor(() => expect(AgentApi.addCustomer).toHaveBeenCalled());
    await waitFor(() =>
      expect(screen.getByTestId('customer-1')).toBeInTheDocument()
    );
  });

  test('opens Add Property modal, verifies disabled state, fills form, and submits new property', async () => {
    AgentApi.addProperty.mockResolvedValue({
      id: 10,
      title: 'Luxury Apartment',
      address: '123 Main St',
      price: 250000,
      isNewConstruction: true,
    });

    render(<AgentDashboard />);

    const openModalBtn = screen.getByText('Add Property');
    fireEvent.click(openModalBtn);

    const submitBtn = screen.getByTestId('add-property-modal-submit');
    expect(submitBtn).toBeDisabled();

    await userEvent.type(
      screen.getByTestId('add-property-input-title'),
      'Luxury Apartment'
    );
    await userEvent.type(
      screen.getByTestId('add-property-input-address'),
      '123 Main St'
    );
    await userEvent.type(
      screen.getByTestId('add-property-input-price'),
      '250000'
    );

    const checkbox = screen.getByTestId('add-property-input-new-construction');
    await userEvent.click(checkbox);
    expect(checkbox).toBeChecked();

    expect(submitBtn).toBeEnabled();

    await userEvent.click(submitBtn);

    await waitFor(() => expect(AgentApi.addProperty).toHaveBeenCalled());
    await waitFor(() =>
      expect(screen.getByText('Luxury Apartment')).toBeInTheDocument()
    );
  });
});
