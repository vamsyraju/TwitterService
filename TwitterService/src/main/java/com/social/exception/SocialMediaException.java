package com.social.exception;


public class SocialMediaException extends RuntimeException {

	private static final long serialVersionUID = 1492617009543548860L;

	public SocialMediaException(String message) {
		super(message);
	}

	public SocialMediaException(Throwable th) {
		super(th);
	}


}
