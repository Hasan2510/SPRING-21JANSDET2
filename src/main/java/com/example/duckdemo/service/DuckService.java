package com.example.duckdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.data.repository.DuckRepository;
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.mappers.DuckMapper;

@Service // labelled as a bean (managed by Spring)
public class DuckService {
	
	// Data Access Object
	private DuckRepository duckRepository;
	
	private DuckMapper duckMapper;
	
	@Autowired
	public DuckService(DuckRepository duckRepository, DuckMapper duckMapper) {
		this.duckRepository = duckRepository;
		this.duckMapper = duckMapper;
	}

	public List<DuckDTO> readAllDucks() {
		List<Duck> ducks = duckRepository.findAll();
		List<DuckDTO> returnables = new ArrayList<DuckDTO>();
		
		ducks.forEach(duck -> returnables.add(duckMapper.mapToDTO(duck)));
		return returnables;
	}
	
	public DuckDTO readById(Integer id) {
		Duck duck = duckRepository.findById(id).orElseThrow(() -> {
			return new ResponseStatusException(HttpStatus.NOT_FOUND, "Duck not found! Quackity, quack...");
		});
		
		return duckMapper.mapToDTO(duck);
	}
	
	public DuckDTO createDuck(Duck duck) {
		Duck newDuck = duckRepository.save(duck);
		
		return duckMapper.mapToDTO(newDuck);
	}
	
	public DuckDTO updateDuck(Integer id, Duck duck) {
		Duck duckInDb = duckRepository.findById(id).orElseThrow(EntityNotFoundException::new);
				
		duckInDb.setName(duck.getName());
		duckInDb.setAge(duck.getAge());
		duckInDb.setHabitat(duck.getHabitat());
		duckInDb.setColour(duck.getColour());
		
		Duck updatedDuck = duckRepository.save(duckInDb);
		
		return duckMapper.mapToDTO(updatedDuck);
	}
	
	public void deleteDuck(Integer id) {
		if (!duckRepository.existsById(id)) {
			throw new EntityNotFoundException("Duck does not exist to delete... QUACK!");
		}
		duckRepository.deleteById(id);
	}
	
}
