import { useState, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import InputField from '../../../shared/components/InputField';
import Button from '../../../shared/components/Button';
import { loginUser } from '../api/LoginApi';
import { useAuth } from '../../../shared/context/AuthContext';

const LoginPage = () => {
  const navigate = useNavigate();
  const { login } = useAuth();
  const [formData, setFormData] = useState({ email: '', password: '' });
  const [loading, setLoading] = useState(false);

  const handleChange = useCallback((e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  }, []);

  const handleLogin = useCallback(
    async (e) => {
      e.preventDefault();
      setLoading(true);
      try {
        const data = await loginUser(formData);
        if (!data) return;
        login(data);
      } finally {
        setLoading(false);
      }
    },
    [formData, login]
  );

  const isSubmitDisabled =
    loading || !formData.email.trim() || !formData.password.trim();

  return (
    <div style={styles.container}>
      <h2>Login</h2>
      <form style={styles.form} onSubmit={handleLogin}>
        <InputField
          type='email'
          name='email'
          label='Email'
          value={formData.email}
          onChange={handleChange}
        />
        <InputField
          type='password'
          name='password'
          label='Password'
          value={formData.password}
          onChange={handleChange}
        />
        <Button
          type='submit'
          label={loading ? 'Logging in...' : 'Login'}
          submitDisabled={isSubmitDisabled}
        />
      </form>

      <p>
        Don’t have an account?{' '}
        <span style={styles.link} onClick={() => navigate('/auth/register')}>
          Register
        </span>
      </p>
    </div>
  );
};

const styles = {
  container: {
    maxWidth: '400px',
    margin: '100px auto',
    textAlign: 'center',
    fontFamily: 'Arial, sans-serif',
  },
  form: {
    display: 'flex',
    flexDirection: 'column',
    gap: '15px',
    marginTop: '20px',
  },
  link: { color: '#007BFF', cursor: 'pointer', textDecoration: 'underline' },
};

export default LoginPage;
