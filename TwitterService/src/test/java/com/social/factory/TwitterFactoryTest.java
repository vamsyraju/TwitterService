package com.social.factory;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import twitter4j.Twitter;

public class TwitterFactoryTest {
	@Test
	public void getInstanceTest() {
		TwitterFactory factory = new TwitterFactory();
		Object obj = factory.getInstance();
		Assert.assertThat(obj, CoreMatchers.instanceOf(Twitter.class));
	}
}
