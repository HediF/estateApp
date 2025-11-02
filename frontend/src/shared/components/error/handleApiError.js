import ApiError from './ApiError';

export function handleApiError(error) {
  console.log(error);
  if (error?.response?.data) {
    const data = error.response.data;
    return new ApiError({
      title: data.title,
      details: data.details,
      messageCode: data.messageCode,
      httpStatus: data.httpStatus,
    });
  }

  if (error?.message === 'Network Error') {
    return new ApiError({
      title: 'Network error',
      details: 'Please check your internet connection and try again.',
    });
  }

  return new ApiError();
}
