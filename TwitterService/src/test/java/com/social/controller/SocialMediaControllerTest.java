package com.social.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class SocialMediaControllerTest {
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(
				new SocialMediaController()).build();
	}

	@Test
	public void testSocialMediaDefault() throws Exception {
		mockMvc.perform(get("/socialMedia")).andExpect(status().isOk())
				.andExpect(view().name("twitterTimeLine"));
	}

	@Test
	public void testSocialMediaFaceBook() throws Exception {
		mockMvc.perform(get("/socialMedia?value=Facebook"))
				.andExpect(status().isOk())
				.andExpect(view().name("FacebookTimeLine"));
	}

	@Test
	public void testSocialMediaErrorWithOutMessage() throws Exception {
		mockMvc.perform(get("/socialMediaError")).andExpect(status().isOk())
				.andExpect(view().name("error"))
				.andExpect(model().attribute("errorMessage", "UNKNOWN ERROR"));

	}

	@Test
	public void testSocialMediaErrorWithMessage() throws Exception {
		mockMvc.perform(get("/socialMediaError?message=ERROR"))
				.andExpect(status().isOk()).andExpect(view().name("error"))
				.andExpect(model().attribute("errorMessage", "ERROR"));
	}
}
