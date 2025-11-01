package com.task.estate.exception;

import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
public class BusinessError implements Serializable {

	private static final long serialVersionUID = 2L;

	private final String messageCode;

	private final String title;

	private final String detailMessage;
	public BusinessError(String messageCode, String title, String detailMessage) {
		Objects.requireNonNull(messageCode, "A message code must be given");
		Objects.requireNonNull(detailMessage, "A detail message must be given");

		this.messageCode = messageCode;
		this.title = title;
		this.detailMessage = detailMessage;
	}
}
