package com.task.estate.exception.agent;

import com.task.estate.util.i18n.MessageId;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AgentMessageId implements MessageId {

	AUTHENTICATED_AGENT_NOT_FOUND("01001", HttpStatus.UNAUTHORIZED),
	PROPERTY_NOT_FOUND("01002", HttpStatus.NOT_FOUND),
	CUSTOMER_NOT_FOUND("01003", HttpStatus.NOT_FOUND),
	PROPERTY_NOT_OWNED_BY_AGENT("01004", HttpStatus.FORBIDDEN),
	CUSTOMER_NOT_OWNED_BY_AGENT("01005", HttpStatus.FORBIDDEN),
	CUSTOMER_CREATION_FAILED("01006", HttpStatus.INTERNAL_SERVER_ERROR),
	PROPERTY_CREATION_FAILED("01007", HttpStatus.INTERNAL_SERVER_ERROR);

	private final String messageCode;
	private final HttpStatus httpStatusCode;

	AgentMessageId(String messageCode, HttpStatus httpStatusCode) {
		this.messageCode = messageCode;
		this.httpStatusCode = httpStatusCode;
	}
}
