package com.task.estate.util.i18n;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
@Slf4j
public class MessageResolver {
	private static MessageResolver singletonInstance;

	@Autowired
	private MessageSource messageSource;

	@PostConstruct
	private void postConstruct() {
		if (singletonInstance != null) {
			return;
		}

		synchronized (MessageResolver.class) {
			if (singletonInstance == null) {
				setSingletonInstance(this);
			}
		}
	}

	private static synchronized void setSingletonInstance(MessageResolver instance) {
		singletonInstance = instance;
	}

	private static Optional<MessageSource> getMessageSource() {
		if (singletonInstance == null) {
			return Optional.empty();
		}
		return Optional.ofNullable(singletonInstance.messageSource);
	}

	public static Optional<String> resolveMessage(String messageId, Object... messageParams) {
		return getMessageSource().map(
				messageSource -> messageSource.getMessage(messageId, messageParams, LocaleContextHolder.getLocale()));
	}
}
