package com.example.duckdemo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.service.DuckService;

// This is RestController, it will send back data in an http response body
@RestController
@RequestMapping(path = "/duck") // This controller has a base path of /duck (localhost:8080/duck)
public class DuckController {

	// localhost:8080/
//	@RequestMapping(method = RequestMethod.GET)
	
//	@Autowired // field injection
	private DuckService duckService;
	
	@Autowired // constructor injection (injected from the application context)
	public DuckController(DuckService duckService) {
		this.duckService = duckService;
	}
	
	// localhost:8080/duck
	@GetMapping
	public ResponseEntity<List<DuckDTO>> getAllDucks() {
		
		// Response has headers, a body and a status code
		HttpHeaders httpHeaders = new HttpHeaders(); // Creating some headers
		httpHeaders.add("Location", "1442"); // Adding a header
		
		// Requesting our DuckDTO data from the duckService
		List<DuckDTO> data = duckService.readAllDucks();
		
		// returning a response of type ResponseEntity(Body, Headers, HttpStatus)
		return new ResponseEntity<List<DuckDTO>>(data, httpHeaders, HttpStatus.OK);
	}
	
	// localhost:8080/duck/3
	@GetMapping("/{id}") // {id} is a path variable
	public ResponseEntity<DuckDTO> getDuckById(@PathVariable("id") int id) {
		// The path variable is captured by the @PathVariable annotation
		// - WE MUST SUPPLY A MATCHING NAME WITHIN THE PARENTHESIS
		// - WE MUST SUPPLY AN APPROPRIATE DATA TYPE FOR THE VAR TO CONVERT TO
		
		// Get our duck data using the service
		DuckDTO duck = duckService.readById(id);
		
		// Return the duck data in a response
		return new ResponseEntity<DuckDTO>(duck, HttpStatus.OK);
	}
	
	// Spring Boots version of @PathParam is @RequestParam(defaultValue = "")
	// localhost:8080/duck/alt?id=1
	@GetMapping("/alt")
	public ResponseEntity<DuckDTO> getDuckByIdAlt(@RequestParam("id") int id) {
		// @RequestParam grabs a query parameter from our path
		// - In this case, it is called `id` and MUST BE SUPPLIED
		// - We can make it optional like so: @RequestParam(name = "id", required = false)
		//   - Or @RequestParam(name = "id", defaultValue = "")
		
		
		DuckDTO duck = duckService.readById(id);
		
		return new ResponseEntity<DuckDTO>(duck, HttpStatus.OK);
	}
	
	// localhost:8080/duck/name/fred
	@GetMapping("/name/{name}")
	public ResponseEntity<DuckDTO> getDuckByName(@PathVariable("name") String name) {
		DuckDTO duck = duckService.readByName(name);
		
		return new ResponseEntity<DuckDTO>(duck, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<DuckDTO> createDuck(@Valid @RequestBody Duck duck) {
		// A Duck is retrieved from the incoming request body (the conversion from json to duck is automatic)
		// - `@RequestBody Duck duck` makes this happen
		// - @Valid is used to employ our models validation on the incoming request
		
		DuckDTO newDuck = duckService.createDuck(duck);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(newDuck.getId()));
	
		return new ResponseEntity<DuckDTO>(newDuck, headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DuckDTO> updateDuck(@PathVariable("id") int id,
										   @RequestBody Duck duck) {
		DuckDTO updatedDuck = duckService.updateDuck(id, duck);
		
		return new ResponseEntity<DuckDTO>(updatedDuck, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteDuck(@PathVariable("id") int id) {		
		return new ResponseEntity<Boolean>(duckService.deleteDuck(id), HttpStatus.OK);
	}
	
	// @GetMapping (retrieving something)
	// @PostMapping (creating something)
	// @PutMapping (generalised update)
	// @PatchMapping (specific update)
	// @DeleteMapping (deleting something)
}
