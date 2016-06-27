package com.social.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;

import com.social.exception.SocialMediaException;
import com.social.twitter.entities.TimeLine;
import com.social.twitter.entities.Tweet;

@Service
public class TwitterMediaService implements SocialMediaService {

	@Autowired
	private com.social.factory.TwitterFactory factory;

	@Override
	public TimeLine getTimeLineForUser(String userName, int sizeLimit)
			throws SocialMediaException {
		TimeLine timeLine = new TimeLine();
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userName.trim())) {
			throw new SocialMediaException("UserName Cannot be Empty");
		}
		if(sizeLimit<1){
			throw new SocialMediaException("Size Limit should be greater than 0");
		}
		try {
			Twitter twitter = factory.getInstance();
			ResponseList<Status> responseList = twitter.getUserTimeline(
					userName, new Paging(1, sizeLimit));
			if (responseList != null && responseList.size() > 0) {
				timeLine.setData(mapResponseListToTweetList(responseList));
			} else {
				throw new SocialMediaException("No Tweets found for User "
						+ userName);
			}
		} catch (Exception e) {
			throw new SocialMediaException("Twitter API call failed for user "
					+ userName + " with error " + e.getMessage());
		}
		return timeLine;
	}

	private List<Tweet> mapResponseListToTweetList(
			ResponseList<Status> responseList) {
		List<Tweet> data = new ArrayList<Tweet>();
		for (Status status : responseList) {
			Tweet tweet = new Tweet();
			if (status.getUser() != null) {
				tweet.setProfileImage(status.getUser().getMiniProfileImageURL());
				tweet.setScreenName("@" + status.getUser().getScreenName());
				tweet.setUserName(status.getUser().getName());
			}
			tweet.setRetweetCount(status.getRetweetCount());
			tweet.setTweetContent(status.getText());
			data.add(tweet);
		}
		return data;
	}

	// @Override
	// public TimeLine getTimeLineForUser(String userName, int sizeLimit) {
	// throw new SocialMediaException("Test Error by Vamsi", "CIde");
	// TimeLine timeLine = new TimeLine();
	// List<Tweet> data = new ArrayList<Tweet>();
	// for (int i = 0; i < sizeLimit; i++) {
	// Tweet tweet = new Tweet();
	// tweet.setProfileImage("http://static.wixstatic.com/media/c303d1_1757bb4e02464bf2afff74cc4000e413.jpg_256");
	// tweet.setRetweetCount(23);
	// tweet.setScreenName("dogygod " + i);
	// tweet.setTweetContent("My fav dog " + i);
	// tweet.setUserName(userName);
	// data.add(tweet);
	// }
	// timeLine.setData(data);
	// return timeLine;
	// }

}
