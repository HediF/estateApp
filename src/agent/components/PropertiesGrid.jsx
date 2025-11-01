import PropertyCard from './../../shared/components/PropertyCard';

const PropertiesGrid = ({ properties, onAssigned }) => {
  return (
    <div style={styles.wrapper}>
      {properties && properties.length > 0 ? (
        <div style={styles.grid}>
          {properties.map((property) => (
            <PropertyCard
              key={property.id}
              property={property}
              onAssigned={onAssigned}
            />
          ))}
        </div>
      ) : (
        <div style={styles.empty}>No properties found</div>
      )}
    </div>
  );
};

const styles = {
  wrapper: {
    backgroundColor: 'transparent',
    fontFamily: 'Arial, sans-serif',
  },
  grid: {
    display: 'grid',
    gridTemplateColumns: 'repeat(auto-fill, minmax(250px, 1fr))',
    gap: 16,
  },
  empty: {
    fontSize: 14,
    color: '#9ca3af',
    fontStyle: 'italic',
    textAlign: 'center',
    padding: 16,
  },
};

export default PropertiesGrid;
