package com.social.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import com.social.exception.TwitterException;
import com.social.twitter.entities.TimeLine;
import com.social.twitter.entities.Tweet;

@Service
public class TwitterMediaService implements SocialMediaService {

	@Override
	public TimeLine getTimeLineForUser(String userName, int sizeLimit)
			throws TwitterException {
		TimeLine timeLine = new TimeLine();
		if(userName.equalsIgnoreCase("")){
			throw new TwitterException("UserName Cannot be Empty");
		}
		try {
			 Twitter twitter = TwitterFactory.getSingleton();
			ResponseList<Status> responseList = twitter.getUserTimeline(
					userName, new Paging(1, sizeLimit));
			if (responseList == null || responseList.size() < 1) {
				throw new TwitterException("No Tweets Found");
			}
			timeLine.setData(mapResponseListToTweetList(responseList));
		} catch (Exception e) {
			e.printStackTrace();
			throw new TwitterException(e);
		}
		return timeLine;
	}

	private List<Tweet> mapResponseListToTweetList(
			ResponseList<Status> responseList) {
		List<Tweet> data = new ArrayList<Tweet>();
		if (responseList != null && responseList.size() > 0) {
			for (Status status : responseList) {
				Tweet tweet = new Tweet();
				tweet.setProfileImage(status.getUser().getMiniProfileImageURL());
				tweet.setRetweetCount(status.getRetweetCount());
				tweet.setScreenName("@" + status.getUser().getScreenName());
				tweet.setTweetContent(status.getText());
				tweet.setUserName(status.getUser().getName());
				data.add(tweet);
			}
		}
		return data;
	}


}
