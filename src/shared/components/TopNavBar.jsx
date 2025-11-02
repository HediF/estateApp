import Button from './Button';
import { useAuth } from '../context/AuthContext';

const TopNavBar = ({ title, actions = [] }) => {
  const { logout } = useAuth();

  return (
    <header style={styles.wrapper}>
      <div style={styles.inner}>
        <div style={styles.left}>
          {title ? <h2 style={styles.title}>{title}</h2> : null}
        </div>

        <div style={styles.right}>
          <nav style={styles.actions}>
            {actions.map(({ key, label, onClick, variant }, idx) => (
              <Button
                key={key || `${label}-${idx}`}
                label={label}
                onClick={onClick}
                variant={variant || 'primary'}
                testID={`navbar-${key}-btn`}
              />
            ))}
          </nav>

          <Button label='Logout' onClick={logout} variant='danger' />
        </div>
      </div>
    </header>
  );
};

const styles = {
  wrapper: {
    position: 'sticky',
    top: 0,
    width: '100%',
    background: '#fff',
    borderBottom: '1px solid #e5e7eb',
    boxShadow: '0 1px 2px rgba(0,0,0,0.04)',
    zIndex: 1000,
  },
  inner: {
    maxWidth: 1100,
    margin: '0 auto',
    padding: '12px 16px',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  left: {
    display: 'flex',
    alignItems: 'center',
  },
  title: {
    margin: 0,
    fontSize: 18,
    fontWeight: 600,
    fontFamily: 'Inter, Arial, sans-serif',
    color: '#111827',
  },
  right: {
    display: 'flex',
    alignItems: 'center',
    gap: 12,
  },
  actions: {
    display: 'flex',
    alignItems: 'center',
    gap: 8,
  },
};

export default TopNavBar;
