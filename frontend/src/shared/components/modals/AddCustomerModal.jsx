import { useState, useCallback } from 'react';
import BaseModal from './BaseModal';
import InputField from '../InputField';

const AddCustomerModal = ({ isOpen, onClose, onSubmit, testID }) => {
  const [form, setForm] = useState({ name: '', email: '', password: '' });

  const handleChange = useCallback((e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  }, []);

  const handleSubmit = useCallback(() => {
    onSubmit(form);
    onClose();
  }, [form, onSubmit, onClose]);

  const isSubmitDisabled =
    !form.name.trim() || !form.email.trim() || !form.password.trim();

  return (
    <BaseModal
      isOpen={isOpen}
      title='Add Customer'
      onClose={onClose}
      onSubmit={handleSubmit}
      submitLabel='Add Customer'
      submitDisabled={isSubmitDisabled}
      testID={testID}
    >
      <div style={styles.form}>
        <InputField
          label='Name'
          name='name'
          value={form.name}
          onChange={handleChange}
          testID={'add-customer-input-name'}
        />
        <InputField
          label='Email'
          name='email'
          value={form.email}
          onChange={handleChange}
          testID={'add-customer-input-email'}
        />
        <InputField
          label='Password'
          name='password'
          type='password'
          value={form.password}
          onChange={handleChange}
          testID={'add-customer-input-password'}
        />
      </div>
    </BaseModal>
  );
};

const styles = {
  form: {
    display: 'flex',
    flexDirection: 'column',
    gap: '12px',
  },
};

export default AddCustomerModal;
