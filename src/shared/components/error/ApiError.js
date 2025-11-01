export default class ApiError extends Error {
  constructor({ title, details, messageCode, httpStatus } = {}) {
    super(details || title || 'Something went wrong');
    this.name = 'ApiError';
    this.title = title || 'Unexpected Error';
    this.details = details || 'Something went wrong. Please try again later.';
    this.messageCode = messageCode || null;
    this.httpStatus = httpStatus || 500;
  }
}
