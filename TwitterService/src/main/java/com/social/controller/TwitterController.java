package com.social.controller;

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

	@Autowired
	SocialMediaService service;
	private static final String GET_TIMELINES = "/getTimeLines";
	private static final String USERNAME = "userName";
	private static final String DEFAULT_USERNAME = "salesforce";
	private static final String SIZE_LIMIT = "sizeLimit";
	private static final String DEFAULT_SIZE_LIMIT = "10";

	@RequestMapping(value = { GET_TIMELINES }, method = RequestMethod.GET)
	public @ResponseBody TimeLine getTimeLine(
			@RequestParam(value = USERNAME, required = true, defaultValue = DEFAULT_USERNAME) String userName,
			@RequestParam(value = SIZE_LIMIT, required = false, defaultValue = DEFAULT_SIZE_LIMIT) int sizeLimit)
			throws SocialMediaException {
		return service.getRecentTimeLineForUser(userName, sizeLimit);
	}

}
