package com.example.duckdemo.controller;

import java.util.List;

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
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.mappers.DuckMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// by default, @SpringBootTest only launches the application context
// - specify a port, it will also launch the Tomcat server on the specified port
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // configures the MockMvc object (used to send requests to our API)
@Sql(scripts = { "classpath:test-schema.sql", "classpath:test-data.sql" },
	 executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class DuckControllerIntegrationTest {

	@Autowired
	private MockMvc mvc; // we use this to send our http requests
	
	@Autowired
	private DuckMapper duckMapper;
	
	@Autowired
	private ObjectMapper objectMapper; // convert objects to and from json
	
	private Duck validDuck = new Duck(1, "Barry", "blue", "pub", 0);
	private DuckDTO duckDTO = new DuckDTO(1, "Barry", "blue", "pub");
	
	private List<Duck> validDucks = List.of(validDuck);
	private List<DuckDTO> validDuckDTOs = List.of(duckDTO);
	
	@Test
	public void createDuckTest() throws Exception {
		Duck duckToSave = new Duck("Fred", "Green", "Jupiter", 4);
		DuckDTO expectedDuck = new DuckDTO(2, "Fred", "Green", "Jupiter");
		
		// Created a mock request to send a POST request to /duck
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/duck");
		
		// Specify the data to be sent & the type of the data
		mockRequest.contentType(MediaType.APPLICATION_JSON); // Mime-Type
		mockRequest.content(objectMapper.writeValueAsString(duckToSave)); // sending Duck in
		
		// Specify what data type we expect in response
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		// Setup ResultMatchers
		// - these are used to compare the result of the request with our own specified values
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();
		
		// Expecting the json returned in the response body to be equal to the json string specified
		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.json(objectMapper.writeValueAsString(expectedDuck)); // expecting DuckDTO back
		
		ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location", "2");
		
		// Send the request and assert the expected results from the result matchers
		mvc.perform(mockRequest)
		   .andExpect(statusMatcher)
		   .andExpect(contentMatcher)
		   .andExpect(headerMatcher);
	}
}
