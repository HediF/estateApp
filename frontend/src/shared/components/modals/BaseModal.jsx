import Button from '../Button';

const BaseModal = ({
  isOpen,
  title,
  onClose,
  children,
  onSubmit,
  submitLabel,
  submitDisabled,
  testID,
}) => {
  if (!isOpen) return null;

  return (
    <div
      style={styles.overlay}
      data-testid={testID ? `${testID}-overlay` : undefined}
    >
      <div style={styles.modal}>
        <div style={styles.header}>
          <h3 style={styles.title}>{title}</h3>
          <Button
            label='✕'
            onClick={onClose}
            variant='secondary'
            testID={testID ? `${testID}-close` : undefined}
          />
        </div>
        <div style={styles.content}>{children}</div>
        {onSubmit && (
          <div style={styles.footer}>
            <Button
              label={submitLabel || 'Submit'}
              onClick={onSubmit}
              submitDisabled={submitDisabled}
              testID={testID ? `${testID}-submit` : undefined}
            />
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
    borderRadius: 10,
    width: '420px',
    maxWidth: '90%',
    padding: '20px 24px',
    boxShadow: '0 6px 16px rgba(0,0,0,0.15)',
    display: 'flex',
    flexDirection: 'column',
    gap: '20px',
  },
  header: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    borderBottom: '1px solid #e5e7eb',
    paddingBottom: 10,
  },
  title: {
    fontSize: 18,
    fontWeight: 600,
    margin: 0,
  },
  content: {
    display: 'flex',
    flexDirection: 'column',
    gap: '14px',
    paddingTop: 4,
  },
  footer: {
    display: 'flex',
    justifyContent: 'flex-end',
    borderTop: '1px solid #f1f1f1',
    paddingTop: 12,
  },
};

export default BaseModal;
