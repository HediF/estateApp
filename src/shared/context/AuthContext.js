import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useState,
} from 'react';
import { useNavigate } from 'react-router-dom';
import { ROUTES, STORAGE_KEYS, USER_TYPE } from './../AppConstants';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const navigate = useNavigate();
  const [user, setUser] = useState(() => {
    const storedUser = localStorage.getItem(STORAGE_KEYS.USER);
    return storedUser ? JSON.parse(storedUser) : null;
  });

  const [token, setToken] = useState(() =>
    localStorage.getItem(STORAGE_KEYS.ACCESS_TOKEN)
  );

  const login = (data) => {
    const { accessToken, ...userData } = data;
    localStorage.setItem(STORAGE_KEYS.ACCESS_TOKEN, accessToken);
    localStorage.setItem(STORAGE_KEYS.USER, JSON.stringify(userData));

    setToken(accessToken);
    setUser(userData);

    if (userData.userType === USER_TYPE.AGENT) navigate(ROUTES.AGENT_DASHBOARD);
    else if (userData.userType === USER_TYPE.CUSTOMER)
      navigate(ROUTES.CUSTOMER_DASHBOARD);
  };

  const logout = useCallback(() => {
    localStorage.removeItem(STORAGE_KEYS.ACCESS_TOKEN);
    localStorage.removeItem(STORAGE_KEYS.USER);
    setUser(null);
    setToken(null);
    navigate(ROUTES.AUTH_LOGIN);
  }, [navigate]);

  useEffect(() => {
    const storedToken = localStorage.getItem(STORAGE_KEYS.ACCESS_TOKEN);
    if (!storedToken && user) logout();
  }, [user, logout]);

  return (
    <AuthContext.Provider
      value={{ user, token, login, logout, isAuthenticated: !!token }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
