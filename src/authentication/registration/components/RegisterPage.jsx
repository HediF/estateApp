import { useState, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import InputField from '../../../shared/components/InputField';
import Button from '../../../shared/components/Button';
import { registerAgent } from '../api/RegistrationApi';
import { useAuth } from '../../../shared/context/AuthContext';

const RegisterPage = () => {
  const navigate = useNavigate();
  const { login } = useAuth();

  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: '',
    registrationCode: '',
  });

  const [loading, setLoading] = useState(false);

  const handleChange = useCallback((e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  }, []);

  const handleRegister = useCallback(
    async (e) => {
      e.preventDefault();
      setLoading(true);
      try {
        const data = await registerAgent(formData);
        if (!data) return;
        login(data);
      } finally {
        setLoading(false);
      }
    },
    [formData, login]
  );

  const isSubmitDisabled =
    loading ||
    !formData.name.trim() ||
    !formData.email.trim() ||
    !formData.password.trim() ||
    !formData.registrationCode.trim();

  return (
    <div style={styles.container}>
      <h2>Register as Agent</h2>
      <form style={styles.form} onSubmit={handleRegister}>
        <InputField
          name='name'
          label='Full Name'
          value={formData.name}
          onChange={handleChange}
        />
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
        <InputField
          name='registrationCode'
          label='Registration Code'
          value={formData.registrationCode}
          onChange={handleChange}
        />
        <Button
          type='submit'
          label={loading ? 'Registering...' : 'Register'}
          submitDisabled={isSubmitDisabled}
        />
      </form>

      <p>
        Already have an account?{' '}
        <span style={styles.link} onClick={() => navigate('/auth/login')}>
          Login
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

export default RegisterPage;
