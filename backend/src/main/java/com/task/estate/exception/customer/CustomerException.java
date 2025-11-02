package com.task.estate.exception.customer;

import com.task.estate.exception.EstateBusinessException;
import com.task.estate.util.i18n.MessageId;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CustomerException extends EstateBusinessException {
	private static final long serialVersionUID = 1L;

	public CustomerException(@NotNull MessageId messageId, Object... messageParams) {
		super(messageId, messageParams);
	}
}