package com.social.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Not Authorized to use the service")
public class TwitterException extends RuntimeException {

	private static final long serialVersionUID = 1492617009543548860L;

	public TwitterException(String message) {
		super(message);
	}

	public TwitterException(Throwable th) {
		super(th);
	}

	public TwitterException() {
		super();
	}
}
