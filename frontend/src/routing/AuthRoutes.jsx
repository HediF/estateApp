import { Routes, Route } from 'react-router-dom';
import PublicRoute from './PublicRoute';
import LoginPage from './../authentication/login/components/LoginPage';
import RegisterPage from './../authentication/registration/components/RegisterPage';

const AuthRoutes = () => {
  return (
    <Routes>
      <Route
        path='login'
        element={
          <PublicRoute>
            <LoginPage />
          </PublicRoute>
        }
      />
      <Route
        path='register'
        element={
          <PublicRoute>
            <RegisterPage />
          </PublicRoute>
        }
      />
    </Routes>
  );
};

export default AuthRoutes;
