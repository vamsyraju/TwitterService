package com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.social.service.SocialMediaService;
import com.social.twitter.entities.TimeLine;

@Controller
public class TwitterController {

	@Autowired
	SocialMediaService service;

	@RequestMapping(value = { "/getTimeLines" }, method = RequestMethod.GET)
	public @ResponseBody TimeLine getTimeLine(
			@RequestParam(value = "userName", required = true, defaultValue = "salesforce") String userName,
			@RequestParam(value = "sizeLimit", required = false, defaultValue = "10") int sizeLimit) {
		return service.getTimeLineForUser(userName, sizeLimit);
	}
}
