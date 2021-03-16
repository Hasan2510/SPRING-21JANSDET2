package com.example.duckdemo.controller;

import java.util.List;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.mappers.DuckMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema-duck.sql",
                "classpath:test-data-duck.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
class DuckControllerIntegrationTest {

        @Autowired
        private MockMvc mvc;

        @Autowired
        private DuckMapper duckMapper;

        @Autowired
        private ObjectMapper objectMapper;

        private Duck validDuck = new Duck(1, "barry", "blue", "pond", 3);
        private DuckDTO validDuckDTO = new DuckDTO(1, "barry", "blue", "pond");

        private List<Duck> validDucks = List.of(validDuck);
        private List<DuckDTO> validDuckDTOs = List.of(validDuckDTO);

        @Test
        void createDuckTest() throws Exception {
                Duck duckToSave = new Duck("fred", "green", "mars", 4);
                DuckDTO expectedDuck = new DuckDTO(2, "fred", "green", "mars");

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.POST, "/duck");

                mockRequest.contentType(MediaType.APPLICATION_JSON);
                mockRequest.content(objectMapper.writeValueAsString(duckToSave));
                mockRequest.accept(MediaType.APPLICATION_JSON);

                ResultMatcher statusMatcher = MockMvcResultMatchers.status().isCreated();

                ResultMatcher contentMatcher = MockMvcResultMatchers.content()
                                .json(objectMapper.writeValueAsString(expectedDuck));

                ResultMatcher headerMatcher = MockMvcResultMatchers.header().string("Location",
                                String.valueOf(expectedDuck.getId()));

                mvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher).andExpect(headerMatcher);

        }

}
