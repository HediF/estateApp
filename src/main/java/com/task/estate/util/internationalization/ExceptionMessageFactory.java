package com.task.estate.util.internationalization;

import com.task.estate.exception.EstateBusinessException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.NoSuchMessageException;

@Slf4j
@Getter
public class ExceptionMessageFactory {

	private static final String EXCEPTION_MESSAGE_PREFIX = "exception";
	private static final String EXCEPTION_TITLE_FALLBACK = "Title not found for message id \"%s\".";
	private static final String EXCEPTION_DETAILS_FALLBACK = "Details not found message id \"%s\".";

	public static EstateBusinessException createBusinessException(MessageId id, Object... params) {
		String titleMsg = fetchTitle(id.getMessageCode(), params);
		String detailMsg = fetchDetails(id.getMessageCode(), params);
		return new EstateBusinessException(id.getHttpStatusCode(), id.getMessageCode(), titleMsg, detailMsg);
	}

	public static String fetchTitle(String id, Object... params) {
		return fetchMessageOrDefault(id, MessagePart.TITLE, params, String.format(EXCEPTION_TITLE_FALLBACK, id));
	}
	public static String fetchDetails(String id, Object... params) {
		return fetchMessageOrDefault(id, MessagePart.DETAILS, params, String.format(EXCEPTION_DETAILS_FALLBACK, id));
	}
	private static String fetchMessageOrDefault(String id, MessagePart part, Object[] params, String defaultMsg) {
		try {
			return MessageResolver.resolveMessage(constructMessageFullText(id, part), params).orElseGet(() -> {
				log.warn("No exception message available for this id: {}", id);
				return defaultMsg;
			});
		} catch (NoSuchMessageException e) {
			log.warn("The lookup resource was not found for id: {}", id);
			return defaultMsg;
		}
	}
	private static String constructMessageFullText(String code, MessagePart part) {
		return String.join(".", EXCEPTION_MESSAGE_PREFIX, code, part.getPart());
	}

	private ExceptionMessageFactory() throws InstantiationException {
		throw new InstantiationException("Instantiation not permitted for this helper class.");
	}
}
