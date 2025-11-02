export default function ErrorDialog({ error, onClose }) {
  if (!error) return null;

  return (
    <div
      style={{
        position: 'fixed',
        inset: 0,
        backgroundColor: 'rgba(0,0,0,0.4)',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        zIndex: 9999,
      }}
    >
      <div
        style={{
          background: '#fff',
          borderRadius: '10px',
          padding: '24px',
          width: '320px',
          boxShadow: '0 4px 10px rgba(0,0,0,0.25)',
          textAlign: 'center',
        }}
      >
        <h2
          style={{ fontSize: '18px', fontWeight: '600', marginBottom: '8px' }}
        >
          {error.title}
        </h2>
        <p style={{ color: '#555', fontSize: '14px', marginBottom: '16px' }}>
          {error.details}
        </p>
        <button
          onClick={onClose}
          style={{
            padding: '8px 16px',
            background: '#2563eb',
            color: 'white',
            border: 'none',
            borderRadius: '6px',
            cursor: 'pointer',
          }}
        >
          OK
        </button>
      </div>
    </div>
  );
}
