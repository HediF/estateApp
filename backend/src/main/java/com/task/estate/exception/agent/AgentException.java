package com.task.estate.exception.agent;

import com.task.estate.exception.EstateBusinessException;
import com.task.estate.exception.BusinessError;
import com.task.estate.util.i18n.MessageId;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AgentException extends EstateBusinessException {

	private final MessageId messageId;

	public AgentException(MessageId messageId, Object... messageParams) {
		super(messageId, messageParams);
		this.messageId = messageId;
	}

	public AgentException(MessageId messageId) {
		super(messageId);
		this.messageId = messageId;
	}
}
