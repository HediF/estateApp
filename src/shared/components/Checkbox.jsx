const Checkbox = ({ label, name, checked, onChange }) => {
  return (
    <div style={styles.container}>
      <label style={styles.label}>
        <input
          type='checkbox'
          name={name}
          checked={checked}
          onChange={onChange}
          style={styles.input}
        />
        {label}
      </label>
    </div>
  );
};

const styles = {
  container: {
    display: 'flex',
    alignItems: 'center',
  },
  label: {
    fontSize: '14px',
    color: '#333',
    display: 'flex',
    alignItems: 'center',
    gap: '6px',
  },
  input: {
    width: '16px',
    height: '16px',
    cursor: 'pointer',
  },
};

export default Checkbox;
