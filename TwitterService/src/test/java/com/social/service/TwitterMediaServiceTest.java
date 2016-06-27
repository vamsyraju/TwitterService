package com.social.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import twitter4j.JSONArray;
import twitter4j.JSONObject;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;
import twitter4j.User;

import com.social.exception.SocialMediaException;
import com.social.factory.TwitterFactory;
import com.social.twitter.entities.TimeLine;

public class TwitterMediaServiceTest {
	public static String JSON_RESPONSE = "[{text:\"Hello\", \"truncated\": true}]";

	@Test
	public void getUserTimelineHappyPath() throws Exception {
		TwitterFactory factory = EasyMock.createMock(TwitterFactory.class);
		Twitter t = EasyMock.createMock(Twitter.class);
		EasyMock.expect(factory.getInstance()).andReturn(t);

		EasyMock.expect(
				t.getUserTimeline(EasyMock.isA(String.class),
						EasyMock.isA(Paging.class))).andReturn(
				buildUserTimelineResponseList(JSON_RESPONSE));
		EasyMock.replay(factory);
		EasyMock.replay(t);
		TwitterMediaService service = new TwitterMediaService();
		ReflectionTestUtils.setField(service, "factory", factory);
		TimeLine timeLine = service.getTimeLineForUser("salesforce", 10);
		Assert.assertEquals(1, timeLine.getData().size());
		Assert.assertEquals("url", timeLine.getData().get(0).getProfileImage());
		Assert.assertEquals("@ScreenName", timeLine.getData().get(0)
				.getScreenName());
		Assert.assertEquals("Tweet Text", timeLine.getData().get(0)
				.getTweetContent());
		Assert.assertEquals("salesforce", timeLine.getData().get(0)
				.getUserName());
		Assert.assertEquals(20, timeLine.getData().get(0).getRetweetCount());
	}

	@Test(expected = SocialMediaException.class)
	public void getUserTimelineUserNameEmpty() {
		TwitterMediaService service = new TwitterMediaService();
		service.getTimeLineForUser("", 10);

	}
	
	@Test(expected = SocialMediaException.class)
	public void getUserTimelineUserNameEmptySpace() {
		TwitterMediaService service = new TwitterMediaService();
		service.getTimeLineForUser(" ", 10);

	}
	
	@Test(expected = SocialMediaException.class)
	public void getUserTimelineSizeLessThenOne() {
		TwitterMediaService service = new TwitterMediaService();
		service.getTimeLineForUser("vamsy_raju", 0);

	}
	
	@Test(expected = SocialMediaException.class)
	public void getUserTimelineReturnNullResponse() throws Exception {
		TwitterFactory factory = EasyMock.createMock(TwitterFactory.class);
		Twitter t = EasyMock.createMock(Twitter.class);
		ResponseList<Status> responseList = null;
		EasyMock.expect(factory.getInstance()).andReturn(t);

		EasyMock.expect(
				t.getUserTimeline(EasyMock.isA(String.class),
						EasyMock.isA(Paging.class))).andReturn(responseList);
		EasyMock.replay(factory);
		EasyMock.replay(t);
		TwitterMediaService service = new TwitterMediaService();
		ReflectionTestUtils.setField(service, "factory", factory);
		service.getTimeLineForUser("salesforce", 10);
	}

	@Test(expected = SocialMediaException.class)
	public void getUserTimelineReturnEmtpyResponse() throws Exception {
		TwitterFactory factory = EasyMock.createMock(TwitterFactory.class);
		Twitter t = EasyMock.createMock(Twitter.class);
		ResponseList<Status> responseList = null;
		EasyMock.expect(factory.getInstance()).andReturn(t);
		responseList = EasyMock.createMock(ResponseList.class);
		EasyMock.expect(responseList.size()).andReturn(0).anyTimes();
		EasyMock.expect(
				t.getUserTimeline(EasyMock.isA(String.class),
						EasyMock.isA(Paging.class))).andReturn(responseList);
		EasyMock.replay(factory);
		EasyMock.replay(t);
		EasyMock.replay(responseList);
		TwitterMediaService service = new TwitterMediaService();
		ReflectionTestUtils.setField(service, "factory", factory);
		service.getTimeLineForUser("salesforce", 10);
	}
	
	@Test(expected = SocialMediaException.class)
	public void getUserTimelineNetworkException() throws TwitterException {
		TwitterFactory factory = EasyMock.createMock(TwitterFactory.class);
		Twitter t = EasyMock.createMock(Twitter.class);
		TwitterException exception = EasyMock
				.createMock(TwitterException.class);
		EasyMock.expect(exception.getMessage()).andReturn("Twitter Service Test error");
		EasyMock.replay(exception);
		EasyMock.expect(factory.getInstance()).andReturn(t);
		EasyMock.expect(
				t.getUserTimeline(EasyMock.isA(String.class),
						EasyMock.isA(Paging.class))).andThrow(exception);
		EasyMock.replay(factory);
		EasyMock.replay(t);
		TwitterMediaService service = new TwitterMediaService();
		ReflectionTestUtils.setField(service, "factory", factory);
		service.getTimeLineForUser("SalesForce", 10);
	}
	
	@Test(expected = SocialMediaException.class)
	public void getUserTimelineSecurityException() throws TwitterException {
		TwitterFactory factory = EasyMock.createMock(TwitterFactory.class);
		Twitter t = EasyMock.createMock(Twitter.class);
		TwitterException exception = EasyMock
				.createMock(TwitterException.class);
		EasyMock.expect(exception.getMessage()).andReturn("Twitter Service Test error");
		EasyMock.replay(exception);
		EasyMock.expect(factory.getInstance()).andReturn(t);
		EasyMock.expect(
				t.getUserTimeline(EasyMock.isA(String.class),
						EasyMock.isA(Paging.class))).andThrow(exception);
		EasyMock.replay(factory);
		EasyMock.replay(t);
		TwitterMediaService service = new TwitterMediaService();
		ReflectionTestUtils.setField(service, "factory", factory);
		service.getTimeLineForUser("SalesForce", 10);
	}
	@Test(expected = SocialMediaException.class)
	public void getUserTimelineTwitterUnHandledException() throws TwitterException {
		TwitterFactory factory = EasyMock.createMock(TwitterFactory.class);
		Twitter t = EasyMock.createMock(Twitter.class);
		TwitterException exception = EasyMock
				.createMock(TwitterException.class);
		EasyMock.expect(exception.getMessage()).andReturn("Twitter Service Test error");
		EasyMock.replay(exception);
		EasyMock.expect(factory.getInstance()).andReturn(t);
		EasyMock.expect(
				t.getUserTimeline(EasyMock.isA(String.class),
						EasyMock.isA(Paging.class))).andThrow(exception);
		EasyMock.replay(factory);
		EasyMock.replay(t);
		TwitterMediaService service = new TwitterMediaService();
		ReflectionTestUtils.setField(service, "factory", factory);
		service.getTimeLineForUser("SalesForce", 10);
	}
	@Test(expected = SocialMediaException.class)
	public void getUserTimelineUnHandledException() throws TwitterException {
		TwitterFactory factory = EasyMock.createMock(TwitterFactory.class);
		Twitter t = EasyMock.createMock(Twitter.class);
		NullPointerException exception = EasyMock
				.createMock(NullPointerException.class);
		EasyMock.expect(factory.getInstance()).andReturn(t);
		EasyMock.expect(
				t.getUserTimeline(EasyMock.isA(String.class),
						EasyMock.isA(Paging.class))).andThrow(exception);
		EasyMock.replay(factory);
		EasyMock.replay(t);
		TwitterMediaService service = new TwitterMediaService();
		ReflectionTestUtils.setField(service, "factory", factory);
		service.getTimeLineForUser("SalesForce", 10);
	}


	@SuppressWarnings("unchecked")
	private static ResponseList<Status> buildUserTimelineResponseList(
			String twitterRawJSONResponse) throws Exception {
		Status st = EasyMock.mock(Status.class);
		User user = EasyMock.mock(User.class);
		ResponseList<Status> responseList = null;
		JSONArray array = new JSONArray(twitterRawJSONResponse);
		final List<Status> list = new ArrayList<Status>();
		if (array.length() > 0) {
			for (int x = 0; x < array.length(); x++) {
				JSONObject obj = (JSONObject) array.get(x);
				Status status = TwitterObjectFactory.createStatus(obj
						.toString());
				list.add(status);
			}
		}
		responseList = EasyMock.createMock(ResponseList.class);
		EasyMock.expect(responseList.toArray(EasyMock.isA(Status[].class)))
				.andDelegateTo(list).anyTimes();
		EasyMock.expect(responseList.size()).andReturn(1).anyTimes();
		Iterator<Status> ite = EasyMock.mock(Iterator.class);
		EasyMock.expect(ite.next()).andReturn(st).anyTimes();
		EasyMock.expect(st.getUser()).andReturn(user).anyTimes();
		EasyMock.expect(st.getRetweetCount()).andReturn(20).anyTimes();
		EasyMock.expect(st.getText()).andReturn("Tweet Text").anyTimes();
		EasyMock.replay(st);
		EasyMock.expect(user.getMiniProfileImageURL()).andReturn("url");
		EasyMock.expect(user.getScreenName()).andReturn("ScreenName");
		EasyMock.expect(user.getName()).andReturn("salesforce");
		EasyMock.replay(user);
		EasyMock.expect(ite.hasNext()).andReturn(true).andReturn(false);
		EasyMock.replay(ite);
		EasyMock.expect(responseList.iterator()).andReturn(ite);
		EasyMock.replay(responseList);

		return responseList;
	}
}
