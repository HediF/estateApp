package com.task.estate.util.i18n;

import org.springframework.http.HttpStatus;

public interface MessageId {
	String getMessageCode();
	HttpStatus getHttpStatusCode();
}
