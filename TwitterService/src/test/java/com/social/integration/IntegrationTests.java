package com.social.integration;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.social.main.Application;
import com.social.twitter.entities.TimeLine;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
public class IntegrationTests {
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private RestTemplate restTemplate = new TestRestTemplate();
	private static HttpEntity<String> request;
	@Before
	public void setUp() {
		String plainCreds = "admin:secret";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		 request= new HttpEntity<String>(headers);
	}

	@Test
	public void testGetTimeLine() {
		HashMap<String, String> urlVariables = new HashMap<String, String>(2);
		urlVariables.put("userName", "vamsy_raju");
		urlVariables.put("sizeLimit", "10");
		final ResponseEntity<TimeLine> responseEntity = restTemplate.exchange(
				"http://localhost:8080/getTimeLines", HttpMethod.GET, request,
				TimeLine.class, urlVariables);
		Assert.assertEquals(10, responseEntity.getBody().getData().size());
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testSocialMedia() {
		HashMap<String, String> urlVariables = new HashMap<String, String>(1);
		urlVariables.put("value", "twitter");
		final ResponseEntity<String> responseEntity = restTemplate
				.exchange("http://localhost:8080/socialMedia",HttpMethod.GET,request,
						String.class,urlVariables);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void testSocialMediaError() {
		HashMap<String, String> urlVariables = new HashMap<String, String>(1);
		urlVariables.put("message", "ErrorMessage");
		final ResponseEntity<String> responseEntity = restTemplate
				.exchange("http://localhost:8080/socialMediaError",HttpMethod.GET,request,
						String.class, urlVariables);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
}
