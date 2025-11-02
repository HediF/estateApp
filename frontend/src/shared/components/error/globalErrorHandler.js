import ApiError from './ApiError';

let showErrorFn = null;

export const registerGlobalErrorHandler = (fn) => {
  showErrorFn = fn;
};

export const globalErrorHandler = (error) => {
  if (showErrorFn) showErrorFn(error);
  else
    console.error(
      '[GLOBAL ERROR]',
      error instanceof ApiError ? error.details : error
    );
};
