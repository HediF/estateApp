package com.task.estate.util.internationalization;

import org.springframework.http.HttpStatus;

public interface MessageId {
	String getMessageCode();
	HttpStatus getHttpStatusCode();
}
