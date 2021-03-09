package com.example.duckdemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.mappers.DuckMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-data.sql", "classpath:test-schema.sql" },
	 executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class DuckControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private DuckMapper duckMapper;

	@Autowired
	private ObjectMapper mapper;
	
	private final Duck TEST_DUCK_FROM_DB = new Duck(1, "Barry", "blue", "pub", 0);
	
	
	@Test
	void testCreateDuck() throws Exception {
		Duck newDuck = new Duck("Earls", "GreyOne", "Beach", 3);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/duck");
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(this.mapper.writeValueAsString(newDuck));
		mockRequest.accept(MediaType.APPLICATION_JSON);

		Duck savedDuck = new Duck(2, newDuck.getName(), newDuck.getColour(), newDuck.getHabitat(), newDuck.getAge());

		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content()
				.json(this.mapper.writeValueAsString(duckMapper.mapToDTO(savedDuck)));
		
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}
}
