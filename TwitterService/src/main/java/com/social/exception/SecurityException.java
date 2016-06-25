package com.social.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Un authorized to perform this operation")
public class SecurityException extends SocialMediaException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7260794761662209971L;

	public SecurityException(String message) {
		super(message);
	}

	public SecurityException(Throwable th) {
		super(th);
	}


}
