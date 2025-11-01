import { Routes, Route } from 'react-router-dom';
import ProtectedRoute from './ProtectedRoute';
import AgentDashboard from './../agent/AgentDashboard';

const AgentRoutes = () => {
  return (
    <Routes>
      <Route
        path='dashboard'
        element={
          <ProtectedRoute>
            <AgentDashboard />
          </ProtectedRoute>
        }
      />
    </Routes>
  );
};

export default AgentRoutes;
