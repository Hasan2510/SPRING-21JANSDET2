package com.example.duckdemo.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.service.DuckService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/duck")
@CrossOrigin // Enables CORS
public class DuckController {

    @Autowired
    private DuckService duckService;

    @GetMapping
    public ResponseEntity<List<DuckDTO>> getAllDucks() {
        List<DuckDTO> data = duckService.readAllDucks();

        return new ResponseEntity<List<DuckDTO>>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DuckDTO> getDuckById(@PathVariable("id") int id) {
        DuckDTO duck = duckService.readById(id);
        return new ResponseEntity<DuckDTO>(duck, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DuckDTO> createDuck(@Valid @RequestBody Duck duck) {
        DuckDTO newDuck = duckService.createDuck(duck);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", String.valueOf(newDuck.getId()));

        return new ResponseEntity<DuckDTO>(newDuck, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DuckDTO> updateDuck(@PathVariable("id") int id, @RequestBody Duck duck) {
        DuckDTO updatedDuckDTO = duckService.updateDuck(id, duck);
        return new ResponseEntity<DuckDTO>(updatedDuckDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDuck(@PathVariable("id") int id) {
        boolean response = duckService.deleteDuck(id);
        return new ResponseEntity<Boolean>(response, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<DuckDTO> getDuckByName(@PathVariable("name") String name) {
        DuckDTO duck = duckService.readByName(name);
        return new ResponseEntity<DuckDTO>(duck, HttpStatus.OK);
    }

}
