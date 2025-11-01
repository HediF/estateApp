import Button from '../Button';

const BaseModal = ({
  isOpen,
  title,
  onClose,
  children,
  onSubmit,
  submitLabel,
}) => {
  if (!isOpen) return null;

  return (
    <div style={styles.overlay}>
      <div style={styles.modal}>
        <div style={styles.header}>
          <h3 style={styles.title}>{title}</h3>
          <Button label='✕' onClick={onClose} variant='secondary' />
        </div>

        <div style={styles.content}>{children}</div>

        {onSubmit && (
          <div style={styles.footer}>
            <Button label={submitLabel || 'Submit'} onClick={onSubmit} />
          </div>
        )}
      </div>
    </div>
  );
};

const styles = {
  overlay: {
    position: 'fixed',
    top: 0,
    left: 0,
    width: '100%',
    height: '100%',
    backgroundColor: 'rgba(0,0,0,0.4)',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    zIndex: 2000,
  },
  modal: {
    backgroundColor: '#fff',
    borderRadius: 8,
    width: '400px',
    maxWidth: '90%',
    padding: '20px',
    boxShadow: '0 4px 12px rgba(0,0,0,0.1)',
    position: 'relative',
  },
  header: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    borderBottom: '1px solid #eee',
    paddingBottom: 8,
    marginBottom: 16,
  },
  title: {
    fontSize: 18,
    fontWeight: '600',
    margin: 0,
  },
  content: {
    fontFamily: 'Arial, sans-serif',
  },
  footer: {
    display: 'flex',
    justifyContent: 'flex-end',
    marginTop: 20,
  },
};

export default BaseModal;
