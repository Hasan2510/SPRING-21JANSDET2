package com.example.duckdemo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.data.repository.DuckRepository;
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.mappers.DuckMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

// Starts the application but not on a server
@SpringBootTest // DOES NOT START THE TOMCAT SERVER (NOT RUNNING ON PORT 8080, OR ANY PORT FOR THAT MATTER)
// The h2 in-memory database is created when @SpringBootTest is ran
public class DuckServiceIntegrationTest {

	@Autowired
	private DuckService duckService;
	
	@Autowired
	private DuckRepository duckRepository;
	
	@Autowired
	private DuckMapper duckMapper;
	
	private List<Duck> ducks;
	private List<DuckDTO> duckDTOs;
	
	private Duck validDuck;
	private DuckDTO validDuckDTO;
	
	@BeforeEach
	public void init() {
		// setup our valid duck data to be saved to the db
		validDuck = new Duck("bob", "red", "beach", 6);
		
		// initialise our lists
		ducks = new ArrayList<Duck>();
		duckDTOs = new ArrayList<DuckDTO>();
				
		// Reset the state of the db before each test
		duckRepository.deleteAll();
		
		// prepopulate the db (get the saved duck back)
		validDuck = duckRepository.save(validDuck);
		
		// map the saved duck to a DTO
		validDuckDTO = duckMapper.mapToDTO(validDuck);
		
		// add the saved duck and corresponding DTO to the relevant lists
		ducks.add(validDuck);
		duckDTOs.add(validDuckDTO);
	}
	
	@Test
	public void readAllDucksTest() {
		// Get all ducks stored in the db
		List<DuckDTO> ducksInDb = duckService.readAllDucks();
		
		// compare to our expected values
		assertThat(duckDTOs).isEqualTo(ducksInDb);
	}
	
}
