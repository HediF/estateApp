package com.task.estate.exception.authentication;

import com.task.estate.exception.EstateBusinessException;
import com.task.estate.util.internationalization.MessageId;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AuthenticationException extends EstateBusinessException {
	private static final long serialVersionUID = 1L;

	public AuthenticationException(@NotNull MessageId messageId, Object... messageParams) {
		super(messageId, messageParams);
	}

}

