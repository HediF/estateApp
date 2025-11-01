import { useState, useCallback } from 'react';
import BaseModal from './BaseModal';
import InputField from '../InputField';
import Checkbox from '../Checkbox';

const AddPropertyModal = ({ isOpen, onClose, onSubmit }) => {
  const [form, setForm] = useState({
    title: '',
    address: '',
    price: '',
    isNewConstruction: false,
  });

  const handleChange = useCallback((e) => {
    const { name, type, value, checked } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value,
    }));
  }, []);

  const handleSubmit = useCallback(() => {
    const parsedData = {
      ...form,
      price: form.price ? parseFloat(form.price) : null,
    };
    onSubmit(parsedData);
    onClose();
  }, [form, onSubmit, onClose]);

  return (
    <BaseModal
      isOpen={isOpen}
      title='Add Property'
      onClose={onClose}
      onSubmit={handleSubmit}
      submitLabel='Add Property'
    >
      <div style={styles.form}>
        <InputField
          label='Title'
          name='title'
          value={form.title}
          onChange={handleChange}
        />
        <InputField
          label='Address'
          name='address'
          value={form.address}
          onChange={handleChange}
        />
        <InputField
          label='Price'
          name='price'
          type='number'
          value={form.price}
          onChange={handleChange}
        />
        <Checkbox
          label='New Construction'
          name='isNewConstruction'
          checked={form.isNewConstruction}
          onChange={handleChange}
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

export default AddPropertyModal;
