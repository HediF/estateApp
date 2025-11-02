package com.task.estate.exception.authentication;

import com.task.estate.util.i18n.MessageId;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AuthenticationMessageId implements MessageId {
	USER_ALREADY_EXISTS("00001", HttpStatus.CONFLICT),

	INVALID_CREDENTIALS("00002", HttpStatus.BAD_REQUEST),

	USER_AUTHENTICATION_FAILED("00003", HttpStatus.INTERNAL_SERVER_ERROR),
	INVALID_VERIFICATION_CODE("00004", HttpStatus.BAD_REQUEST);
	;
	private final String messageCode;
	private final HttpStatus httpStatusCode;

	AuthenticationMessageId(String messageCode, HttpStatus httpStatusCode) {
		this.messageCode = messageCode;
		this.httpStatusCode = httpStatusCode;
	}
}
