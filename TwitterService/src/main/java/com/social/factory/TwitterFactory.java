package com.social.factory;

import org.springframework.stereotype.Component;

import twitter4j.Twitter;

@Component
public class TwitterFactory {
	private final twitter4j.TwitterFactory factory;

	public TwitterFactory() {
		factory = new twitter4j.TwitterFactory();
	}

	public Twitter getInstance() {
		return factory.getInstance();
	}
}
