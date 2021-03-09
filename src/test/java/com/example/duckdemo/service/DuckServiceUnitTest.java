package com.example.duckdemo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.data.repository.DuckRepository;
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.mappers.DuckMapper;

@SpringBootTest
public class DuckServiceUnitTest {

	@Autowired
	private DuckService duckService;
	
	@MockBean
	private DuckRepository duckRepository;
	
	@MockBean
	private DuckMapper duckMapper;
	
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
	public void readAllDucksTest() {
		// When a specific method is called on a mock object,
		// we can specify what is returned
		when(duckRepository.findAll()).thenReturn(ducks);
		when(duckMapper.mapToDTO(validDuck)).thenReturn(validDuckDTO);
		
		// When the service is called, this will initiate the
		// when-then returns
		assertThat(duckDTOs).isEqualTo(duckService.readAllDucks()); // true or false
		
		// Verify that our methods on our mock objects were called
		verify(duckRepository, times(1)).findAll();
		verify(duckMapper, times(1)).mapToDTO(validDuck);
	}
	
	@Test
	public void updateDuckTest() {
		Duck updatedDuck = new Duck(3, "fred", "red", "beach", 6);
		DuckDTO updatedDuckDTO = new DuckDTO(3, "fred", "red", "beach");
		
		when(duckRepository.findById(Mockito.any(Integer.class)))
			.thenReturn(Optional.of(validDuck));
		
		when(duckRepository.save(Mockito.any(Duck.class)))
			.thenReturn(updatedDuck);
		
		when(duckMapper.mapToDTO(Mockito.any(Duck.class)))
			.thenReturn(updatedDuckDTO);
		
		DuckDTO toTestDTO = duckService.updateDuck(validDuck.getId(), updatedDuck);
		
		assertThat(updatedDuckDTO).isEqualTo(toTestDTO);
	}
}
