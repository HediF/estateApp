package com.task.estate.util.internationalization;

import lombok.Getter;

@Getter
public enum MessagePart {
	TITLE("title"),

	DETAILS("details"),

	;

	private final String part;

	MessagePart(String part) {
		this.part = part;
	}
}
