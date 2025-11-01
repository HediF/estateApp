import { AuthProvider } from './shared/context/AuthContext';
import RootRoutes from './routing/RootRoutes';

const App = () => (
  <AuthProvider>
    <RootRoutes />
  </AuthProvider>
);

export default App;
