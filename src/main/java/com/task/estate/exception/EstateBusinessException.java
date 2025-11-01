package com.task.estate.exception;

import com.task.estate.util.internationalization.ExceptionMessageFactory;
import com.task.estate.util.internationalization.MessageId;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Getter
public class EstateBusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatusCode;

	private final BusinessError businessError;

	public EstateBusinessException(HttpStatus httpStatusCode, BusinessError businessError) {
		super(renderExceptionDetailMessage(businessError));
		Objects.requireNonNull(httpStatusCode, "A HTTP status code must be provided");
		this.httpStatusCode = httpStatusCode;
		this.businessError = businessError;
	}

	public EstateBusinessException(HttpStatus httpStatusCode, String messageCode, String title,
								   String detailMessage) {
		this(httpStatusCode, new BusinessError(messageCode, title, detailMessage));
	}

	public EstateBusinessException(MessageId messageId, Object... messageParams) {
		this(ExceptionMessageFactory.createBusinessException(messageId, messageParams));
	}

	protected EstateBusinessException(EstateBusinessException sourceException) {
		this(sourceException.getHttpStatusCode(), sourceException.getBusinessError());
	}

	private static String renderExceptionDetailMessage(BusinessError businessError) {
		Objects.requireNonNull(businessError, "A business error should not be null");

		StringBuilder resultMessage = new StringBuilder("");
		if (resultMessage.length() > 0) {
			resultMessage.append(System.lineSeparator());
		}
		resultMessage.append(businessError.getDetailMessage());
		return resultMessage.toString();
	}

}
