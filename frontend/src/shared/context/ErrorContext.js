import { registerGlobalErrorHandler } from '../components/error/globalErrorHandler';
import ApiError from '../components/error/ApiError';
import ErrorDialog from '../components/error/ErrorDialog';
import {
  createContext,
  useContext,
  useState,
  useCallback,
  useEffect,
} from 'react';

const ErrorContext = createContext();

export function ErrorProvider({ children }) {
  const [error, setError] = useState(null);

  const showError = useCallback((err) => {
    if (!err) return;
    if (err instanceof ApiError) setError(err);
    else
      setError(
        new ApiError({
          title: 'Unexpected Error',
          details: 'Something went wrong. Please try again later.',
        })
      );
  }, []);

  const hideError = useCallback(() => setError(null), []);

  useEffect(() => {
    registerGlobalErrorHandler(showError);
  }, [showError]);

  return (
    <ErrorContext.Provider value={{ showError }}>
      {children}
      <ErrorDialog
        error={error ? { title: error.title, details: error.details } : null}
        onClose={hideError}
      />
    </ErrorContext.Provider>
  );
}

export function useError() {
  return useContext(ErrorContext);
}
