package com.social.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.social.service.SocialMediaService;
import com.social.twitter.entities.TimeLine;
import com.social.twitter.entities.Tweet;

public class TwitterControllerTest {
	private MockMvc mockMvc;

	@Mock
	SocialMediaService mockService;

	@InjectMocks
	TwitterController controller;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockService = Mockito.mock(SocialMediaService.class);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testGetTimeLineDefault() throws Exception {
		TimeLine timeLine = buildTimeLine();
		Mockito.when(mockService.getTimeLineForUser("salesforce", 10))
				.thenReturn(timeLine);
		mockMvc.perform(get("/getTimeLines"))
				.andExpect(status().isOk());
//				.andExpect(
//						jsonPath("$.data[0].userName",
//								Matchers.equalToIgnoringCase("salesforce")));

	}

	private TimeLine buildTimeLine() {
		TimeLine timeLine = new TimeLine();
		List<Tweet> data = new ArrayList<Tweet>();
		for (int i = 0; i < 10; i++) {
			Tweet tweet = new Tweet();
			tweet.setProfileImage("http://static.wixstatic.com/media/c303d1_1757bb4e02464bf2afff74cc4000e413.jpg_256");
			tweet.setRetweetCount(23);
			tweet.setScreenName("dogygod " + i);
			tweet.setTweetContent("My fav dog " + i);
			tweet.setUserName("salesforce");
			data.add(tweet);
		}
		timeLine.setData(data);
		return timeLine;
	}
}
