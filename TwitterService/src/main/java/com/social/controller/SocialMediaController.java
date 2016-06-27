package com.social.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SocialMediaController {

	private static final String TIMELINE = "TimeLine";
	private static final String ERROR = "error";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String SOCIAL_MEDIA_URI = "/socialMedia";
	private static final String SOCIAL_MEDIA_ERROR_URI = "/socialMediaError";
	private static final String VALUE = "value";
	private static final String MESSAGE = "message";
	private static final String DEFAULT_VALUE = "twitter";
	private static final String DEFAULT_MESSAGE = "UNKNOWN ERROR";
	
	

	@RequestMapping(SOCIAL_MEDIA_URI)
	public String socialMedia(
			@RequestParam(value = VALUE, required = true, defaultValue = DEFAULT_VALUE) String value) {
		return value + TIMELINE;
	}

	@RequestMapping(SOCIAL_MEDIA_ERROR_URI)
	public String socialMediaError(
			@RequestParam(value = MESSAGE, required = false, defaultValue = DEFAULT_MESSAGE) String message,
			Model model) {
		model.addAttribute(ERROR_MESSAGE, message);
		return ERROR;
	}
}
