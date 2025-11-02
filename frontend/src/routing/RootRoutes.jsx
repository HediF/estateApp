import { Routes, Route, Navigate } from 'react-router-dom';
import AuthRoutes from './AuthRoutes';
import AgentRoutes from './AgentRoutes';
import CustomerRoutes from './CustomerRoutes';

const RootRoutes = () => {
  return (
    <Routes>
      <Route path='/auth/*' element={<AuthRoutes />} />
      <Route path='/agent/*' element={<AgentRoutes />} />
      <Route path='/customer/*' element={<CustomerRoutes />} />
      <Route path='*' element={<Navigate to='/auth/register' replace />} />
    </Routes>
  );
};

export default RootRoutes;
