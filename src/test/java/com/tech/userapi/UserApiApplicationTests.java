package com.tech.userapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.userapi.controller.request.PhoneRequest;
import com.tech.userapi.controller.request.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserApiApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
		this.mockMvc.perform(
				post("/api/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(UserRequest.builder()
						.name("test")
						.email("test@test.com")
						.password("22aaiIO$$")
						.phones(Arrays.asList(PhoneRequest.builder()
								.phone("123")
								.cityCode("1")
								.countryCode("2")
								.build()))
						.build()))
		).andExpect(status().isCreated());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
