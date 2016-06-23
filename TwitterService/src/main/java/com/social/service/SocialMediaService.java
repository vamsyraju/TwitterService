package com.social.service;

import com.social.twitter.entities.TimeLine;

public interface SocialMediaService {
	public TimeLine getTimeLineForUser(String userName, int sizeLimit);
}
