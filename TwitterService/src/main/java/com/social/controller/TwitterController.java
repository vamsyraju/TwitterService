package com.social.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.social.exception.SocialMediaException;
import com.social.service.SocialMediaService;
import com.social.twitter.entities.TimeLine;

@Controller
public class TwitterController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SocialMediaService service;

	@RequestMapping(value = { "/getTimeLines" }, method = RequestMethod.GET)
	public @ResponseBody TimeLine getTimeLine(
			@RequestParam(value = "userName", required = true, defaultValue = "salesforce") String userName,
			@RequestParam(value = "sizeLimit", required = false, defaultValue = "10") int sizeLimit)
			throws SocialMediaException {
		try {
			log.debug("getTimeLine controller Called with parameters: UserName: "
					+ userName + ", SizeLimit: " + sizeLimit);
			TimeLine tl = service.getTimeLineForUser(userName, sizeLimit);
			return tl;
		} catch (SocialMediaException se) {
			log.error("ERROR: " + se.getMessage());
			throw se;
		}
	}

}
