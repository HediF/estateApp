const Button = ({ label, onClick, type = 'button', variant = 'primary' }) => {
  const getButtonStyle = () => {
    switch (variant) {
      case 'secondary':
        return { ...styles.button, backgroundColor: '#6c757d' };
      case 'danger':
        return { ...styles.button, backgroundColor: '#dc3545' };
      default:
        return styles.button;
    }
  };

  return (
    <button type={type} onClick={onClick} style={getButtonStyle()}>
      {label}
    </button>
  );
};

const styles = {
  button: {
    padding: '10px',
    backgroundColor: '#007BFF',
    color: 'white',
    border: 'none',
    borderRadius: '4px',
    cursor: 'pointer',
    fontSize: '16px',
    transition: 'background-color 0.2s ease',
  },
};

export default Button;
