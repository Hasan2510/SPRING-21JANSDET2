package com.example.duckdemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.duckdemo.data.model.Pond;
import com.example.duckdemo.data.repository.PondRepository;
import com.example.duckdemo.dto.PondDTO;
import com.example.duckdemo.mappers.PondMapper;

@RestController
@RequestMapping("/pond")
public class PondController {
	
	private PondRepository pondRepository;
	
	private PondMapper pondMapper;
	
	@Autowired
	public PondController(PondRepository pondRepository, PondMapper pondMapper) {
		this.pondRepository = pondRepository;
		this.pondMapper = pondMapper;
	}

	@GetMapping
	public ResponseEntity<List<PondDTO>> readAllPonds() {
		List<Pond> ponds = pondRepository.findAll();
		List<PondDTO> pondDTOs = new ArrayList<PondDTO>();
		
		ponds.forEach(pond -> pondDTOs.add(pondMapper.mapToDTO(pond)));
		
		return new ResponseEntity<List<PondDTO>>(pondDTOs, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Pond> createPond(@RequestBody Pond pond) {
		Pond savedPond = pondRepository.save(pond);
		
		return new ResponseEntity<Pond>(savedPond, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletePondById(@PathVariable("id") Integer id) {
		pondRepository.deleteById(id);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
