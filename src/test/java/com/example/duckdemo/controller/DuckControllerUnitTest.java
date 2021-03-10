package com.example.duckdemo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.service.DuckService;

@SpringBootTest // Given us an Application Context with our beans
public class DuckControllerUnitTest {

	@Autowired
	private DuckController duckController;
	
	@MockBean // Create a mock (fake object)
	private DuckService duckService;
	
	private List<Duck> ducks;
	private List<DuckDTO> duckDTOs;
	
	private Duck validDuck;
	private DuckDTO validDuckDTO;
	
	@BeforeEach
	public void init() {
		validDuck = new Duck(3, "bob", "red", "beach", 6);
		validDuckDTO = new DuckDTO(3, "bob", "red", "beach");
		
		ducks = new ArrayList<Duck>();
		duckDTOs = new ArrayList<DuckDTO>();
		
		ducks.add(validDuck);
		duckDTOs.add(validDuckDTO);
	}
	
	@Test
	public void getAllDucksTest() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Location", "1442");
		
		when(duckService.readAllDucks()).thenReturn(duckDTOs);
		
		ResponseEntity<List<DuckDTO>> response = 
				new ResponseEntity<List<DuckDTO>>(duckDTOs, httpHeaders, HttpStatus.OK);
		
		assertThat(response).isEqualTo(duckController.getAllDucks());
		
		verify(duckService, times(1)).readAllDucks();
	}
	
	@Test
	public void createDuckTest() {
		when(duckService.createDuck(Mockito.any(Duck.class))).thenReturn(validDuckDTO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validDuckDTO.getId()));
		
		ResponseEntity<DuckDTO> response = 
				new ResponseEntity<DuckDTO>(validDuckDTO, headers, HttpStatus.CREATED);
		
		assertThat(response).isEqualTo(duckController.createDuck(validDuck));
		
		verify(duckService, times(1)).createDuck(Mockito.any(Duck.class));
	}
	
	@Test
	public void deleteDuckTest() {
		// We only need to mock deleteDuck
		when(duckService.deleteDuck(Mockito.any(Integer.class))).thenReturn(true);
		
		// expected response
		ResponseEntity<Boolean> response =
				new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
		// Checking our expected response is equal to the actual response
		// of the controller.deleteDuck method
		assertThat(response).isEqualTo(duckController.deleteDuck(validDuck.getId()));
		
		verify(duckService, times(1)).deleteDuck(Mockito.any(Integer.class));
	}
	
	@Test
	public void updateDuckTest() {
		// mock the update duck method
		when(duckService.updateDuck(Mockito.any(Integer.class), Mockito.any(Duck.class)))
			.thenReturn(validDuckDTO);
		
		// expected response
		ResponseEntity<DuckDTO> response =
				new ResponseEntity<DuckDTO>(validDuckDTO, HttpStatus.OK);
		
		// check our response
		assertThat(response).isEqualTo(duckController.updateDuck(validDuck.getId(), validDuck));
		
		// verify the response
		verify(duckService, times(1)).updateDuck(Mockito.any(Integer.class), Mockito.any(Duck.class));
	}
}
