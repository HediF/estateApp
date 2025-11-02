package com.task.estate.api;

import com.task.estate.authentication.v1.gen.model.ErrorMessage;
import com.task.estate.exception.BusinessError;
import com.task.estate.exception.EstateBusinessException;
import com.task.estate.exception.common.CommonMessageId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AbstractEstateControllerAdvice<T> {

	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<Object> handleConflict(NullPointerException exception) {
		log.error("NullPointerException occurred: {}", exception.getMessage());
		EstateBusinessException businessException = new EstateBusinessException(
				CommonMessageId.GENERAL_INTERNAL_SERVER_ERROR, "npe");

		return new ResponseEntity<>(createErrorMessagesForBusinessException(businessException),
				businessException.getHttpStatusCode());
	}

	@ExceptionHandler(EstateBusinessException.class)
	protected ResponseEntity<Object> handleEstateBusinessException(EstateBusinessException exception) {
		log.error("Business error occurred: {}", exception.getMessage());
		log.trace(exception.getMessage(), exception);

		return new ResponseEntity<>(createErrorMessagesForBusinessException(exception), exception.getHttpStatusCode());
	}

	protected ErrorMessage createErrorMessagesForBusinessException(EstateBusinessException exception) {
		if (exception == null) {
			log.warn("Attempted to create error message for null exception");
			return new ErrorMessage();
		}

		BusinessError businessError = exception.getBusinessError();
		if (businessError == null) {
			log.warn("Business error in exception is null");
			return new ErrorMessage();
		}

		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessageCode(businessError.getMessageCode());
		errorMessage.setTitle(businessError.getTitle());
		errorMessage.setDetails(businessError.getDetailMessage());

		return errorMessage;
	}
}
