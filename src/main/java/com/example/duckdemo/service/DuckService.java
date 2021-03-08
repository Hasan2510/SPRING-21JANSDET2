package com.example.duckdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.data.repository.DuckRepository;

@Service // labelled as a bean (managed by Spring)
public class DuckService {
	
	// Data Access Object
	private DuckRepository duckRepository;
	
	@Autowired
	public DuckService(DuckRepository duckRepository) {
		this.duckRepository = duckRepository;
	}

	public List<Duck> readAllDucks() {
		List<Duck> ducks = duckRepository.findAll();
		return ducks;
	}
	
	public Duck readById(Integer id) throws EntityNotFoundException {
		Optional<Duck> duck = duckRepository.findById(id);
		
		if (duck.isPresent()) {
			return duck.get();
		} else {
			throw new EntityNotFoundException("Duck not found");
		}
	}
	
	public Duck createDuck(Duck duck) {
		Duck newDuck = duckRepository.save(duck);
		
		return newDuck;
	}
	
	public Duck updateDuck(Integer id, Duck duck) throws EntityNotFoundException {
		Duck duckInDb = readById(id);
		
		duckInDb.setName(duck.getName());
		duckInDb.setAge(duck.getAge());
		duckInDb.setHabitat(duck.getHabitat());
		duckInDb.setColour(duck.getColour());
		
		Duck updatedDuck = duckRepository.save(duckInDb);
		
		return updatedDuck;
	}
	
	public void deleteDuck(Integer id) {
		if (!duckRepository.existsById(id)) {
			throw new EntityNotFoundException("Duck does not exist to delete... QUACK!");
		}
		duckRepository.deleteById(id);
	}
	
}
