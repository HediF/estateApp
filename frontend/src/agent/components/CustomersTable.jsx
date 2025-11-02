import CustomerCard from './CustomerCard';

const CustomersTable = ({ customers }) => {
  return (
    <div style={styles.table}>
      <div style={styles.headerRow}>
        <div style={styles.headerCell}>ID</div>
        <div style={styles.headerCell}>Name</div>
        <div style={styles.headerCell}>Email</div>
        <div style={styles.headerCell}>Properties</div>
      </div>

      <div style={styles.body}>
        {customers && customers.length > 0 ? (
          customers.map((customer) => (
            <CustomerCard key={customer.id} customer={customer} />
          ))
        ) : (
          <div style={styles.empty}>No customers found</div>
        )}
      </div>
    </div>
  );
};

const styles = {
  table: {
    border: '1px solid #e5e7eb',
    borderRadius: 8,
    overflow: 'hidden',
    backgroundColor: '#fff',
    fontFamily: 'Arial, sans-serif',
  },
  headerRow: {
    display: 'grid',
    gridTemplateColumns: '60px 1fr 1fr 1fr',
    backgroundColor: '#f9fafb',
    borderBottom: '1px solid #e5e7eb',
    fontWeight: 600,
    fontSize: 14,
    color: '#111827',
    padding: '10px 12px',
  },
  headerCell: {
    textAlign: 'left',
  },
  body: {
    display: 'flex',
    flexDirection: 'column',
  },
  empty: {
    padding: '12px',
    color: '#9ca3af',
    fontStyle: 'italic',
    textAlign: 'center',
  },
};

export default CustomersTable;
