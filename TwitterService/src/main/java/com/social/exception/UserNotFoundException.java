package com.social.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User Not Found")
public class UserNotFoundException extends SocialMediaException {
	private static final long serialVersionUID = -7235544222915056203L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
