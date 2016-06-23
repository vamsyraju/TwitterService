package com.social.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.social.twitter.entities.TimeLine;
import com.social.twitter.entities.Tweet;

@Service
public class TwitterMediaService implements SocialMediaService {

	@Override
	public TimeLine getTimeLineForUser(String userName, int sizeLimit) {
		TimeLine t=new TimeLine();
		List<Tweet> data=new ArrayList<Tweet>();
		Tweet tweet=new Tweet();
		tweet.setProfileImage("");
		tweet.setRetweetCount(3);
		tweet.setScreenName("@Vamsy");
		tweet.setTweetContent("It was a very good day");
		tweet.setUserName(userName);
		data.add(tweet);
		t.setData(data);
		return t;
	}

}
