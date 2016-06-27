package com.social.service;

import com.social.twitter.entities.TimeLine;

public interface SocialMediaService {
	public TimeLine getRecentTimeLineForUser(String userName, int sizeLimit);
}
