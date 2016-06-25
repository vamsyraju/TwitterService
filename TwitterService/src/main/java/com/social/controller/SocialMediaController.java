package com.social.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SocialMediaController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/socialMedia")
	public String socialMedia(
			@RequestParam(value = "value", required = true, defaultValue = "twitter") String value) {
		log.debug("SocialMedia controller Called with parameters: " + value);
		return value + "TimeLine";
	}

	@RequestMapping("/socialMediaError")
	public String socialMediaError(
			@RequestParam(value = "message", required = false, defaultValue = "UNKNOWN ERROR") String message,
			Model model) {
		log.debug("SocialMediaError controller Called with parameters: "
				+ message);
		model.addAttribute("errorMessage", message);
		return "error";
	}
}
