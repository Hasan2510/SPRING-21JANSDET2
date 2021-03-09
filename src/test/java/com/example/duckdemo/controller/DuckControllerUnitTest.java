package com.example.duckdemo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.mappers.DuckMapper;
import com.example.duckdemo.service.DuckService;

@SpringBootTest
public class DuckControllerUnitTest {

	@Autowired
	private DuckController duckController;
	
	@MockBean
	private DuckService duckService;
	
	@Autowired
	private DuckMapper duckMapper;
	
	private List<Duck> duckList;

	private Duck testDuck;

	private Duck testDuckWithID;

	private DuckDTO duckDTO;

	private final int id = 1;
	
	@BeforeEach
	void init() {
		this.duckList = new ArrayList<Duck>();
		this.testDuck = new Duck("Ducktor Doom", "Grey", "Latveria", 3);
		this.testDuckWithID = new Duck(id, "Ducktor Doom", "Grey", "Latveria", 3);
		
		this.duckList.add(testDuckWithID);
		this.duckDTO = duckMapper.mapToDTO(testDuckWithID);
	}
	
	@Test
	void createDuckTest() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(testDuckWithID.getId()));
		
		when(this.duckService.createDuck(testDuck)).thenReturn(duckDTO);
		
		assertThat(new ResponseEntity<DuckDTO>(duckDTO,
												headers,
												HttpStatus.CREATED))
			.isEqualTo(duckController.createDuck(testDuck));
		
		verify(this.duckService, times(1)).createDuck(testDuck);
				
	}
}
