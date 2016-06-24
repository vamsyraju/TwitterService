package com.social.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SocialMediaController {

	@RequestMapping("/socialMedia")
	public String socialMedia(@RequestParam(value="value",required=true,defaultValue="twitter") String value){
		return value+"TimeLine";
	}
	
	@RequestMapping("/socialMediaError")
	public String socialMediaError(@RequestParam(value="message",required=false,defaultValue="UNKNOWN ERROR") String message,Model model){
		model.addAttribute("errorMessage", message);
		return "error";
	}
}
