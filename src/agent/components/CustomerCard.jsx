const CustomerCard = ({ customer }) => {
  const propertyIds =
    customer.properties && customer.properties.length > 0
      ? customer.properties.map((p) => p.id).join(', ')
      : '—';

  return (
    <div style={styles.row}>
      <div style={styles.cellId}>{customer.id}</div>
      <div style={styles.cellName}>
        <img
          src='https://cdn-icons-png.flaticon.com/512/847/847969.png'
          alt='Customer'
          style={styles.avatar}
        />
        <span>{customer.name}</span>
      </div>
      <div style={styles.cellEmail}>{customer.email}</div>
      <div style={styles.cellProperties}>{propertyIds}</div>
    </div>
  );
};

const styles = {
  row: {
    display: 'grid',
    gridTemplateColumns: '60px 1fr 1fr 1fr',
    alignItems: 'center',
    padding: '10px 12px',
    borderBottom: '1px solid #e5e7eb',
    backgroundColor: '#fff',
    fontFamily: 'Arial, sans-serif',
    fontSize: '14px',
  },
  cellId: {
    color: '#6b7280',
  },
  cellName: {
    display: 'flex',
    alignItems: 'center',
    gap: '8px',
    fontWeight: 500,
    color: '#111827',
  },
  cellEmail: {
    color: '#374151',
  },
  cellProperties: {
    color: '#111827',
    fontWeight: 500,
    textAlign: 'left',
  },
  avatar: {
    width: 32,
    height: 32,
    borderRadius: '50%',
  },
};

export default CustomerCard;
