package com.task.estate.exception.common;

import com.task.estate.util.i18n.MessageId;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonMessageId implements MessageId {

	GENERAL_INTERNAL_SERVER_ERROR("90001", HttpStatus.INTERNAL_SERVER_ERROR),
	VALIDATION_FAILED("90002", HttpStatus.BAD_REQUEST),
	RESOURCE_NOT_FOUND("90003", HttpStatus.NOT_FOUND),
	ACCESS_DENIED("90004", HttpStatus.FORBIDDEN),
	INVALID_REQUEST("90005", HttpStatus.BAD_REQUEST),
	OPERATION_NOT_ALLOWED("90006", HttpStatus.METHOD_NOT_ALLOWED);

	private final String messageCode;
	private final HttpStatus httpStatusCode;

	CommonMessageId(String messageCode, HttpStatus httpStatusCode) {
		this.messageCode = messageCode;
		this.httpStatusCode = httpStatusCode;
	}
}
