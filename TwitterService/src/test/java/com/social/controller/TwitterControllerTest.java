package com.social.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.social.exception.SocialMediaException;
import com.social.service.SocialMediaService;
import com.social.twitter.entities.TimeLine;
import com.social.twitter.entities.Tweet;

public class TwitterControllerTest {
	private MockMvc mockMvc;

	@Mock
	SocialMediaService mockService;

	@InjectMocks
	TwitterController controller;

	private TimeLine timeLine;

	@Before
	public void setup() {
		timeLine = buildTimeLine();
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testGetTimeLineDefault() throws Exception {
		Mockito.when(
				mockService.getTimeLineForUser(Mockito.anyString(),
						Mockito.anyInt())).thenReturn(timeLine);
		mockMvc.perform(get("/getTimeLines"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data", hasSize(10)))
				.andExpect(
						jsonPath("$.data[0].userName",
								Matchers.equalToIgnoringCase("salesforce")))
				.andExpect(
						jsonPath(
								"$.data[0].profileImage",
								Matchers.equalToIgnoringCase("http://static.wixstatic.com/media/c303d1_1757bb4e02464bf2afff74cc4000e413.jpg_256")))
				.andExpect(jsonPath("$.data[0].retweetCount", is(23)))
				.andExpect(
						jsonPath("$.data[0].screenName",
								Matchers.equalToIgnoringCase("dogygod0")))
				.andExpect(
						jsonPath("$.data[0].tweetContent",
								Matchers.equalToIgnoringCase("My fav dog 0")));

	}

	@Test
	public void testGetTimeLineWithParameters() throws Exception {
		Mockito.when(
				mockService.getTimeLineForUser(Mockito.anyString(),
						Mockito.anyInt())).thenReturn(timeLine);
		mockMvc.perform(get("/getTimeLines?userName=salesforce&sizeLimit=20"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data", hasSize(10)))
				.andExpect(
						jsonPath("$.data[0].userName",
								Matchers.equalToIgnoringCase("salesforce")))
				.andExpect(
						jsonPath(
								"$.data[0].profileImage",
								Matchers.equalToIgnoringCase("http://static.wixstatic.com/media/c303d1_1757bb4e02464bf2afff74cc4000e413.jpg_256")))
				.andExpect(jsonPath("$.data[0].retweetCount", is(23)))
				.andExpect(
						jsonPath("$.data[0].screenName",
								Matchers.equalToIgnoringCase("dogygod0")))
				.andExpect(
						jsonPath("$.data[0].tweetContent",
								Matchers.equalToIgnoringCase("My fav dog 0")));

	}

	@Test
	public void testGetTimeLineException() throws Exception {
		Mockito.when(
				mockService.getTimeLineForUser(Mockito.anyString(),
						Mockito.anyInt())).thenThrow(
				new SocialMediaException("ALL HELL BROKE LOSE EXCEPTION"));
		try{
			mockMvc.perform(get("/getTimeLines?userName=salesforce&sizeLimit=10"));
				
		}catch(SocialMediaException se){
			Assert.assertEquals("ALL HELL BROKE LOSE EXCEPTION", se.getMessage());;
		}

	}

	private TimeLine buildTimeLine() {
		TimeLine timeLine = new TimeLine();
		List<Tweet> data = new ArrayList<Tweet>();
		for (int i = 0; i < 10; i++) {
			Tweet tweet = new Tweet();
			tweet.setProfileImage("http://static.wixstatic.com/media/c303d1_1757bb4e02464bf2afff74cc4000e413.jpg_256");
			tweet.setRetweetCount(23);
			tweet.setScreenName("dogygod" + i);
			tweet.setTweetContent("My fav dog " + i);
			tweet.setUserName("salesforce");
			data.add(tweet);
		}
		timeLine.setData(data);
		return timeLine;
	}
}
