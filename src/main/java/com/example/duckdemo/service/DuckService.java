package com.example.duckdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	public Duck readById(int id) throws Exception {
		Optional<Duck> duck = duckRepository.findById(id);
		
		if (duck.isPresent()) {
			return duck.get();
		} else {
			throw new Exception("Duck not found");
		}
	}
	
	public Duck createDuck(Duck duck) {
		Duck newDuck = duckRepository.save(duck);
		
		return newDuck;
	}
	
	public Duck updateDuck(int id, Duck duck) {

		return null;
	}
	
	public void deleteDuck(int id) {

	}
	
}
