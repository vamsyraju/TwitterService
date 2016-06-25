package com.social.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.social.exception.NetworkException;
import com.social.exception.SecurityException;
import com.social.exception.SocialMediaException;
import com.social.exception.UnknownCauseException;
import com.social.exception.UserNotFoundException;
import com.social.twitter.entities.TimeLine;
import com.social.twitter.entities.Tweet;

@Service
public class TwitterMediaService implements SocialMediaService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private com.social.factory.TwitterFactory factory;

	@Override
	public TimeLine getTimeLineForUser(String userName, int sizeLimit)
			throws SocialMediaException {
		TimeLine timeLine = new TimeLine();
		if (userName.equalsIgnoreCase("")) {
			throw new UserNotFoundException("UserName Cannot be Empty");
		}
		try {
			Twitter twitter = factory.getInstance();
			ResponseList<Status> responseList = twitter.getUserTimeline(
					userName, new Paging(1, sizeLimit));
			timeLine.setData(mapResponseListToTweetList(responseList));
		} catch (TwitterException te) {
			log.error("Error:" + te.getErrorMessage());
			if (te.isCausedByNetworkIssue()) {
				throw new NetworkException(te);
			} else if (te.getErrorCode() == HttpStatus.UNAUTHORIZED.value()) {
				throw new SecurityException(te);
			} else {
				throw new UnknownCauseException(te);
			}
		} catch (Exception e) {
			log.error("Error:" + e.getMessage());
			throw new UnknownCauseException(e);
		}
		return timeLine;
	}

	private List<Tweet> mapResponseListToTweetList(
			ResponseList<Status> responseList) {
		List<Tweet> data = new ArrayList<Tweet>();
		if (responseList != null && responseList.size() > 0) {
			for (Status status : responseList) {
				Tweet tweet = new Tweet();
				if (status.getUser() != null) {
					tweet.setProfileImage(status.getUser()
							.getMiniProfileImageURL());
					tweet.setScreenName("@" + status.getUser().getScreenName());
					tweet.setUserName(status.getUser().getName());
				}
				tweet.setRetweetCount(status.getRetweetCount());
				tweet.setTweetContent(status.getText());
				data.add(tweet);
			}
		}
		return data;
	}

}
