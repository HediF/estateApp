import { AuthProvider } from './shared/context/AuthContext';
import RootRoutes from './routing/RootRoutes';
import { ErrorProvider } from './shared/context/ErrorContext';

const App = () => (
  <ErrorProvider>
    <AuthProvider>
      <RootRoutes />
    </AuthProvider>
  </ErrorProvider>
);

export default App;
