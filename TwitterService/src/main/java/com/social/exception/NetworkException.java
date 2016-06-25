package com.social.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Timeout or other severe network error")
public class NetworkException extends SocialMediaException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1593729016265462787L;

	public NetworkException(String message) {
		super(message);
	}

	public NetworkException(Throwable th) {
		super(th);
	}


}
