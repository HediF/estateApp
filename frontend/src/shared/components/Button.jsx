const Button = ({
  label,
  onClick,
  type = 'button',
  variant = 'primary',
  submitDisabled,
  testID,
}) => {
  const isDisabled = Boolean(submitDisabled);

  const getButtonStyle = () => {
    if (isDisabled) {
      return {
        ...styles.button,
        backgroundColor: '#cbd5e1',
        color: '#6b7280',
        cursor: 'not-allowed',
        opacity: 0.8,
      };
    }

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
    <button
      type={type}
      onClick={!isDisabled ? onClick : undefined}
      style={getButtonStyle()}
      disabled={isDisabled}
      data-testid={testID}
    >
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
