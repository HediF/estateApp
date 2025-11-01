import { Navigate } from 'react-router-dom';
import { useAuth } from '../shared/context/AuthContext';
import { ROUTES, USER_TYPE } from '../shared/AppConstants';

const PublicRoute = ({ children }) => {
  const { isAuthenticated, user } = useAuth();

  if (isAuthenticated && user?.userType === USER_TYPE.AGENT) {
    return <Navigate to={ROUTES.AGENT_DASHBOARD} replace />;
  }

  if (isAuthenticated && user?.userType === USER_TYPE.CUSTOMER) {
    return <Navigate to={ROUTES.CUSTOMER_DASHBOARD} replace />;
  }

  return children;
};

export default PublicRoute;
