import { useState } from 'react';
import { STORAGE_KEYS, USER_TYPE } from '../AppConstants';
import LinkPropertyToCustomerModal from './modals/LinkPropertyToCustomerModal';

const PropertyCard = ({ property, onAssigned }) => {
  const user = JSON.parse(localStorage.getItem(STORAGE_KEYS.USER) || '{}');
  const isAgent = user?.userType === USER_TYPE.AGENT;
  const [isModalOpen, setModalOpen] = useState(false);

  const handleOpenModal = () => setModalOpen(true);
  const handleCloseModal = () => setModalOpen(false);

  return (
    <>
      <div style={styles.card}>
        <div style={styles.header}>
          <div style={styles.headerLeft}>
            <span style={styles.id}>#{property.id}</span>
            <h3 style={styles.title}>{property.title}</h3>
          </div>
          <span
            style={{
              ...styles.badge,
              backgroundColor: property.isNewConstruction
                ? '#10b981'
                : '#6b7280',
            }}
          >
            {property.isNewConstruction ? 'NEW' : 'OLD'}
          </span>
        </div>

        <div style={styles.body}>
          <div style={styles.row}>
            <span style={styles.label}>Address:</span>
            <span style={styles.value}>{property.address || '—'}</span>
          </div>

          <div style={styles.row}>
            <span style={styles.label}>Price:</span>
            <span style={styles.value}>
              {property.price ? `${property.price.toLocaleString()} €` : '—'}
            </span>
          </div>

          {isAgent && (
            <div style={styles.row}>
              <span style={styles.label}>Customer ID:</span>
              <span style={styles.value}>
                {property.customerId ? (
                  <>
                    <strong>{property.customerId}</strong>{' '}
                    <button onClick={handleOpenModal} style={styles.changeLink}>
                      Change
                    </button>
                  </>
                ) : (
                  <button onClick={handleOpenModal} style={styles.assignLink}>
                    Link to a Customer
                  </button>
                )}
              </span>
            </div>
          )}
        </div>
      </div>

      {isAgent && (
        <LinkPropertyToCustomerModal
          isOpen={isModalOpen}
          propertyId={property.id}
          onClose={handleCloseModal}
          onAssigned={onAssigned}
        />
      )}
    </>
  );
};

const styles = {
  card: {
    border: '1px solid #e5e7eb',
    borderRadius: 10,
    padding: 16,
    backgroundColor: '#ffffff',
    boxShadow: '0 1px 3px rgba(0,0,0,0.08)',
    fontFamily: 'Arial, sans-serif',
    display: 'flex',
    flexDirection: 'column',
    gap: 12,
  },
  header: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    borderBottom: '1px solid #f3f4f6',
    paddingBottom: 8,
  },
  headerLeft: {
    display: 'flex',
    alignItems: 'center',
    gap: 8,
  },
  id: {
    fontSize: 13,
    color: '#6b7280',
    backgroundColor: '#f3f4f6',
    padding: '2px 6px',
    borderRadius: 6,
  },
  title: {
    fontSize: 16,
    fontWeight: 600,
    color: '#111827',
    margin: 0,
  },
  body: {
    display: 'flex',
    flexDirection: 'column',
    gap: 6,
    paddingTop: 4,
  },
  row: {
    display: 'flex',
    justifyContent: 'space-between',
    fontSize: 14,
    alignItems: 'center',
  },
  label: {
    color: '#6b7280',
    fontWeight: 500,
  },
  value: {
    color: '#111827',
    fontWeight: 500,
  },
  badge: {
    padding: '4px 10px',
    borderRadius: 12,
    fontSize: 12,
    fontWeight: 600,
    color: '#ffffff',
    textTransform: 'uppercase',
    letterSpacing: '0.5px',
  },
  assignLink: {
    border: 'none',
    background: 'none',
    color: '#2563eb',
    fontSize: 14,
    textDecoration: 'underline',
    cursor: 'pointer',
    padding: 0,
  },
  changeLink: {
    border: 'none',
    background: 'none',
    color: '#dc2626',
    fontSize: 13,
    textDecoration: 'underline',
    cursor: 'pointer',
    padding: 0,
  },
};

export default PropertyCard;
