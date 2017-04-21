package com.example;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.junit.Test;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


	private final WebTestClient webTestClient = WebTestClient.bindToRouterFunction(
			DemoApplication.getRouter()
	).build();



	@Test
	public void test1() {

		webTestClient.get()
				.uri("/")
				.exchange()
				.expectStatus()
				.is2xxSuccessful()
				.expectBody(String.class)
				.isEqualTo("/test");
	}




}
