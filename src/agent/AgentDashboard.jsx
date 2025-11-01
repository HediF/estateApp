import { useState, useCallback, useEffect } from 'react';
import TopNavBar from './../shared/components/TopNavBar';
import AddCustomerModal from '../shared/components/modals/AddCustomerModal';
import AddPropertyModal from '../shared/components/modals/AddPropertyModal';
import CustomersTable from './components/CustomersTable';
import PropertiesGrid from './components/PropertiesGrid';
import {
  addCustomer,
  addProperty,
  getAllCustomers,
  getAllProperties,
} from './api/AgentApi';

const AgentDashboard = () => {
  const [isCustomerModalOpen, setCustomerModalOpen] = useState(false);
  const [isPropertyModalOpen, setPropertyModalOpen] = useState(false);
  const [customers, setCustomers] = useState([]);
  const [properties, setProperties] = useState([]);

  const handleAddCustomer = useCallback(() => setCustomerModalOpen(true), []);
  const handleAddProperty = useCallback(() => setPropertyModalOpen(true), []);
  const handleCloseCustomerModal = useCallback(
    () => setCustomerModalOpen(false),
    []
  );
  const handleClosePropertyModal = useCallback(
    () => setPropertyModalOpen(false),
    []
  );

  const handleSubmitCustomer = useCallback(async (data) => {
    try {
      const newCustomer = await addCustomer(data);
      setCustomers((prev) => [...prev, newCustomer]);
      setCustomerModalOpen(false);
    } catch (error) {
      console.error('[DASHBOARD] Failed to add customer:', error);
      alert('Failed to add customer');
    }
  }, []);

  const handleSubmitProperty = useCallback(async (data) => {
    try {
      const newProperty = await addProperty(data);
      setProperties((prev) => [...prev, newProperty]);
      setPropertyModalOpen(false);
    } catch (error) {
      console.error('[DASHBOARD] Failed to add property:', error);
      alert('Failed to add property');
    }
  }, []);

  const fetchCustomersAndProperties = useCallback(async () => {
    try {
      const [fetchedCustomers, fetchedProperties] = await Promise.all([
        getAllCustomers(),
        getAllProperties(),
      ]);
      setCustomers(fetchedCustomers);
      setProperties(fetchedProperties);
    } catch (error) {
      console.error('[DASHBOARD] Failed to fetch data:', error);
      alert('Failed to load dashboard data.');
    }
  }, []);

  const handlePropertyAssigned = useCallback(
    (updatedProperty) => {
      setProperties((prev) =>
        prev.map((p) => (p.id === updatedProperty.id ? updatedProperty : p))
      );

      setCustomers((prev) =>
        prev.map((c) => ({
          ...c,
          properties: [
            ...(c.properties?.filter((p) => p.id !== updatedProperty.id) || []),
            ...(c.id === updatedProperty.customerId
              ? [{ id: updatedProperty.id, title: updatedProperty.title }]
              : []),
          ],
        }))
      );
    },
    [setProperties, setCustomers]
  );

  useEffect(() => {
    fetchCustomersAndProperties();
  }, [fetchCustomersAndProperties]);

  return (
    <div>
      <TopNavBar
        title='Agent Dashboard'
        actions={[
          {
            key: 'add-customer',
            label: 'Add Customer',
            onClick: handleAddCustomer,
          },
          {
            key: 'add-property',
            label: 'Add Property',
            onClick: handleAddProperty,
          },
        ]}
      />

      <main style={styles.container}>
        <div style={styles.grid}>
          <div style={styles.left}>
            <h2 style={styles.sectionTitle}>Properties</h2>
            <PropertiesGrid
              properties={properties}
              onAssigned={handlePropertyAssigned}
            />
          </div>

          <div style={styles.right}>
            <h2 style={styles.sectionTitle}>Customers</h2>
            <CustomersTable customers={customers} />
          </div>
        </div>
      </main>

      <AddCustomerModal
        isOpen={isCustomerModalOpen}
        onClose={handleCloseCustomerModal}
        onSubmit={handleSubmitCustomer}
      />

      <AddPropertyModal
        isOpen={isPropertyModalOpen}
        onClose={handleClosePropertyModal}
        onSubmit={handleSubmitProperty}
      />
    </div>
  );
};

const styles = {
  container: {
    maxWidth: '1200px',
    margin: '40px auto',
    padding: '16px',
    fontFamily: 'Arial, sans-serif',
  },
  grid: {
    display: 'grid',
    gridTemplateColumns: '1fr 1fr',
    gap: '24px',
    alignItems: 'flex-start',
  },
  left: {
    backgroundColor: '#f9fafb',
    padding: '12px',
    borderRadius: 8,
    border: '1px solid #e5e7eb',
  },
  right: {
    backgroundColor: '#f9fafb',
    padding: '12px',
    borderRadius: 8,
    border: '1px solid #e5e7eb',
  },
  sectionTitle: {
    fontSize: 18,
    fontWeight: 600,
    color: '#111827',
    marginBottom: 12,
  },
};

export default AgentDashboard;
