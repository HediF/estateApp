import { useState, useCallback } from 'react';
import BaseModal from './BaseModal';
import InputField from '../InputField';
import { assignPropertyToCustomer } from '../../../agent/api/AgentApi';

const LinkPropertyToCustomerModal = ({
  isOpen,
  propertyId,
  onClose,
  onAssigned,
}) => {
  const [customerId, setCustomerId] = useState('');
  const [loading, setLoading] = useState(false);

  const handleAssign = useCallback(async () => {
    if (!customerId) {
      alert('Please enter a customer ID');
      return;
    }

    try {
      setLoading(true);
      const updatedProperty = await assignPropertyToCustomer(
        propertyId,
        Number(customerId)
      );
      onAssigned?.(updatedProperty);
      onClose();
      setCustomerId('');
    } catch (error) {
      console.error('[PROPERTY] Failed to assign customer:', error);
      alert('Failed to assign customer.');
    } finally {
      setLoading(false);
    }
  }, [propertyId, customerId, onAssigned, onClose]);

  return (
    <BaseModal
      isOpen={isOpen}
      title='Link Property to Customer'
      onClose={onClose}
      onSubmit={handleAssign}
      submitLabel={loading ? 'Assigning...' : 'Assign'}
    >
      <InputField
        label='Customer ID'
        name='customerId'
        type='number'
        placeholder='Enter customer ID'
        value={customerId}
        onChange={(e) => setCustomerId(e.target.value)}
      />
    </BaseModal>
  );
};

export default LinkPropertyToCustomerModal;
