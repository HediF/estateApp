import { useEffect, useState, useCallback } from 'react';
import TopNavBar from './../shared/components/TopNavBar';
import PropertiesGrid from '../agent/components/PropertiesGrid';
import { useAuth } from '../shared/context/AuthContext';
import { getCustomerProperties } from './api/CustomerApi';

const CustomerDashboard = () => {
  const { user } = useAuth();
  const [properties, setProperties] = useState([]);

  const fetchProperties = useCallback(async () => {
    try {
      const data = await getCustomerProperties();
      setProperties(data);
    } catch (error) {
      console.error('[CUSTOMER DASHBOARD] Failed to load properties:', error);
      alert('Failed to load your properties.');
    }
  }, []);

  useEffect(() => {
    fetchProperties();
  }, [fetchProperties]);

  return (
    <div>
      <TopNavBar title={`Welcome, ${user?.name || 'Customer'}`} />
      <main style={styles.container}>
        <h2 style={styles.sectionTitle}>Your Properties</h2>
        <PropertiesGrid properties={properties} />
      </main>
    </div>
  );
};

const styles = {
  container: {
    maxWidth: '1000px',
    margin: '40px auto',
    padding: '16px',
    fontFamily: 'Arial, sans-serif',
  },
  sectionTitle: {
    fontSize: 18,
    fontWeight: 600,
    color: '#111827',
    marginBottom: 16,
  },
};

export default CustomerDashboard;
