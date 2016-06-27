package com.social.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${username.empty}")
	private String userNameEmptyError;
	@Value("${size.greater.than.zero}")
	private String sizeGreaterThanZero;
	@Value("${no.tweets.found}")
	private String noTweetsFound;
	@Value("${generic.twitter.exception}")
	private String twitterError;
	private final static String AMP = "@";
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public TimeLine getRecentTimeLineForUser(String userName, int sizeLimit)
			throws SocialMediaException {
		TimeLine timeLine = new TimeLine();
		if (StringUtils.isEmpty(userName)
				|| StringUtils.isEmpty(userName.trim())) {
			log.error(userNameEmptyError + "for User: " + userName);
			throw new SocialMediaException(userNameEmptyError);
		}
		if (sizeLimit < 1) {
			log.error(sizeGreaterThanZero + "for User: " + userName);
			throw new SocialMediaException(sizeGreaterThanZero);
		}
		try {
			Twitter twitter = factory.getInstance();
			log.debug("Call to Twitter API for User "+userName +" STARTED");
			ResponseList<Status> responseList = twitter.getUserTimeline(
					userName, new Paging(1, sizeLimit));
			if (responseList != null && responseList.size() > 0) {
				timeLine.setData(mapResponseListToTweetList(responseList));
			} else {
				log.error(noTweetsFound + "for User: " + userName);
				throw new SocialMediaException(noTweetsFound + userName);
			}
		} catch (Exception e) {
			log.error(twitterError + "for User: " + userName + e.getMessage());
			throw new SocialMediaException(twitterError + userName
					+ " with error " + e.getMessage());
		}
		log.debug("Call to Twitter API for User "+userName +" SUCCESS");
		return timeLine;
	}

	private List<Tweet> mapResponseListToTweetList(
			ResponseList<Status> responseList) {
		List<Tweet> data = new ArrayList<Tweet>();
		for (Status status : responseList) {
			Tweet tweet = new Tweet();
			if (status.getUser() != null) {
				tweet.setProfileImage(status.getUser().getMiniProfileImageURL());
				tweet.setScreenName(AMP + status.getUser().getScreenName());
				tweet.setUserName(status.getUser().getName());
			}
			tweet.setRetweetCount(status.getRetweetCount());
			tweet.setTweetContent(status.getText());
			data.add(tweet);
		}
		return data;
	}

}
