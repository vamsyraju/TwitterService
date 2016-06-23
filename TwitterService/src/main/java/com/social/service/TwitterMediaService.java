package com.social.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import com.social.twitter.entities.TimeLine;
import com.social.twitter.entities.Tweet;

@Service
public class TwitterMediaService implements SocialMediaService {

	//TODO: ADD EXCEPTION HANDLING
	@Override
	public TimeLine getTimeLineForUser(String userName, int sizeLimit) {
		TimeLine timeLine=new TimeLine();
		List<Tweet> data=new ArrayList<Tweet>();
		try{
		Twitter twitter=TwitterFactory.getSingleton();
		ResponseList<Status> statusList=twitter.getUserTimeline(userName, new Paging(1, sizeLimit));
		for(Status status:statusList){
			Tweet tweet=new Tweet();
			tweet.setProfileImage(status.getUser().getMiniProfileImageURL());
			tweet.setRetweetCount(status.getRetweetCount());
			tweet.setScreenName(status.getUser().getScreenName());
			tweet.setTweetContent(status.getText());
			tweet.setUserName(status.getUser().getName());
			data.add(tweet);
		}
		}catch(TwitterException tw){
			tw.printStackTrace();
		}
		timeLine.setData(data);
		return timeLine;
	}

}
