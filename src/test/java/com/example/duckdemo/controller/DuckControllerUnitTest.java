package com.example.duckdemo.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.mappers.DuckMapper;
import com.example.duckdemo.service.DuckService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class DuckControllerUnitTest {

    @Autowired
    private DuckController duckController;

    @MockBean
    private DuckService duckService;

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
    void deleteDuckTest() {
        when(duckService.deleteDuck(Mockito.any(Integer.class))).thenReturn(true);

        ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.OK);

        assertThat(response).isEqualTo(duckController.deleteDuck(validDuck.getId()));

        verify(duckService, times(1)).deleteDuck(Mockito.any(Integer.class));
    }

    @Test
    void updateDuckTest() {
        when(duckService.updateDuck(Mockito.any(Integer.class), Mockito.any(Duck.class))).thenReturn(validDuckDTO);

        ResponseEntity<DuckDTO> response = new ResponseEntity<DuckDTO>(validDuckDTO, HttpStatus.OK);

        assertThat(response).isEqualTo(duckController.updateDuck(validDuck.getId(), validDuck));

        verify(duckService, times(1)).updateDuck(Mockito.any(Integer.class), Mockito.any(Duck.class));
    }

    @Test
    void getAllDucksTest() {
        when(duckService.readAllDucks()).thenReturn(duckDTOs);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Method", "getAllDuckDTOs");
        ResponseEntity<List<DuckDTO>> response = new ResponseEntity<List<DuckDTO>>(duckDTOs, headers, HttpStatus.OK);

        assertThat(response).isEqualTo(duckController.getAllDucks());

        verify(duckService, times(1)).readAllDucks();
    }

    @Test
    void getDucksByIdTest() {
        when(duckService.readById(Mockito.any(Integer.class))).thenReturn(validDuckDTO);

        ResponseEntity<DuckDTO> response = new ResponseEntity<DuckDTO>(validDuckDTO, HttpStatus.OK);

        assertThat(response).isEqualTo(duckController.getDuckById(validDuck.getId()));

        verify(duckService, times(1)).readById(Mockito.any(Integer.class));
    }

    @Test
    void createDuckTest() {
        when(duckService.createDuck(Mockito.any(Duck.class))).thenReturn(validDuckDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", String.valueOf(validDuck.getId()));
        ResponseEntity<DuckDTO> response = new ResponseEntity<DuckDTO>(validDuckDTO, headers, HttpStatus.CREATED);

        assertThat(response).isEqualTo(duckController.createDuck(validDuck));

        verify(duckService, times(1)).createDuck(Mockito.any(Duck.class));
    }

    @Test
    void getDuckByNameTest() {
        when(duckService.readByName(Mockito.any(String.class))).thenReturn(validDuckDTO);

        ResponseEntity<DuckDTO> response = new ResponseEntity<DuckDTO>(validDuckDTO, HttpStatus.OK);

        assertThat(response).isEqualTo(duckController.getDuckByName(validDuck.getName()));

        verify(duckService, times(1)).readByName(Mockito.any(String.class));

    }

}
