import { useState, useCallback } from 'react';
import BaseModal from './BaseModal';
import InputField from '../InputField';
import { assignPropertyToCustomer } from '../../../agent/api/AgentApi';

const LinkPropertyToCustomerModal = ({
  isOpen,
  propertyId,
  onClose,
  onAssigned,
  testID,
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
      if (!updatedProperty) return;
      onAssigned?.(updatedProperty);
      onClose();
      setCustomerId('');
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
      testID={testID}
    >
      <InputField
        label='Customer ID'
        name='customerId'
        type='number'
        placeholder='Enter customer ID'
        value={customerId}
        onChange={(e) => setCustomerId(e.target.value)}
        testID='link-property-input-customer-id'
      />
    </BaseModal>
  );
};

export default LinkPropertyToCustomerModal;
