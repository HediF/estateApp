import { Navigate } from 'react-router-dom';
import { useAuth } from '../shared/context/AuthContext';

const ProtectedRoute = ({ children }) => {
  const { isAuthenticated } = useAuth();
  if (!isAuthenticated) return <Navigate to='/auth/login' replace />;
  return children;
};

export default ProtectedRoute;
