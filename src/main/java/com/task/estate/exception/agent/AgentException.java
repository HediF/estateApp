package com.task.estate.exception.agent;

import com.task.estate.exception.EstateBusinessException;
import com.task.estate.util.internationalization.MessageId;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AgentException extends EstateBusinessException {
	private static final long serialVersionUID = 1L;

	public AgentException(@NotNull MessageId messageId, Object... messageParams) {
		super(messageId, messageParams);
	}
}
