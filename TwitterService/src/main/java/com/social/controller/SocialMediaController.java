package com.social.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SocialMediaController {

	@RequestMapping("/socialMedia")
	public String socialMedia(@RequestParam(value="",required=true,defaultValue="twitter") String value){
		return value+"TimeLine";
	}
}
