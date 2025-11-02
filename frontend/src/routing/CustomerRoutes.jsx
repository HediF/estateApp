import { Routes, Route } from 'react-router-dom';
import ProtectedRoute from './ProtectedRoute';
import CustomerDashboard from './../customer/CustomerDashboard';

const CustomerRoutes = () => {
  return (
    <Routes>
      <Route
        path='dashboard'
        element={
          <ProtectedRoute>
            <CustomerDashboard />
          </ProtectedRoute>
        }
      />
    </Routes>
  );
};

export default CustomerRoutes;
