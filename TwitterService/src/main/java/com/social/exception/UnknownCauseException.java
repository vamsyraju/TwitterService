package com.social.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unknow error")
public class UnknownCauseException extends SocialMediaException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2255634244720976141L;

	public UnknownCauseException(String message) {
		super(message);
	}

	public UnknownCauseException(Throwable th) {
		super(th);
	}

}
