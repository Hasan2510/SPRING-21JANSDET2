package com.example.duckdemo.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.duckdemo.data.model.Pond;
import com.example.duckdemo.dto.PondDTO;
import com.example.duckdemo.service.PondService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pond")
public class PondController {

    private PondService pondService;

    @Autowired
    public PondController(PondService pondService) {
        this.pondService = pondService;
    }

    @GetMapping
    public ResponseEntity<List<PondDTO>> getAllPonds() {
        List<PondDTO> data = pondService.readAllPonds();

        return new ResponseEntity<List<PondDTO>>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PondDTO> getPondById(@PathVariable("id") int id) {
        PondDTO pond = pondService.readById(id);
        return new ResponseEntity<PondDTO>(pond, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PondDTO> createPond(@Valid @RequestBody Pond pond) {
        PondDTO newPond = pondService.createPond(pond);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", String.valueOf(newPond.getId()));

        return new ResponseEntity<PondDTO>(newPond, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PondDTO> updatePond(@PathVariable("id") int id, @RequestBody Pond pond) {
        PondDTO updatedPond = pondService.updatePond(id, pond);
        return new ResponseEntity<PondDTO>(updatedPond, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePond(@PathVariable("id") int id) {
        boolean response = pondService.deletePond(id);
        return new ResponseEntity<Boolean>(response, HttpStatus.OK);
    }

}
