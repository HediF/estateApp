package com.task.estate.exception.customer;

import com.task.estate.util.i18n.MessageId;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomerMessageId implements MessageId {

	AUTHENTICATED_CUSTOMER_NOT_FOUND("02001", HttpStatus.UNAUTHORIZED),
	NO_PROPERTIES_FOUND("02002", HttpStatus.NOT_FOUND),
	PROPERTY_CONVERSION_FAILED("02003", HttpStatus.INTERNAL_SERVER_ERROR);

	private final String messageCode;
	private final HttpStatus httpStatusCode;

	CustomerMessageId(String messageCode, HttpStatus httpStatusCode) {
		this.messageCode = messageCode;
		this.httpStatusCode = httpStatusCode;
	}
}
