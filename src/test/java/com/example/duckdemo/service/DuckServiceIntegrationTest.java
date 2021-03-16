package com.example.duckdemo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.data.repository.DuckRepository;
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.mappers.DuckMapper;

import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DuckServiceIntegrationTest {

    @Autowired
    private DuckService duckService;

    @Autowired
    private DuckRepository duckRepository;

    @Autowired
    private DuckMapper duckMapper;

    private List<Duck> ducks;
    private List<DuckDTO> duckDTOs;

    private Duck validDuck;
    private DuckDTO validDuckDTO;

    @BeforeEach
    public void init() {
        validDuck = new Duck(1, "bob", "red", "beach", 6);
        validDuckDTO = new DuckDTO(1, "bob", "red", "beach");

        ducks = new ArrayList<Duck>();
        duckDTOs = new ArrayList<DuckDTO>();

        duckRepository.deleteAll();

        validDuck = duckRepository.save(validDuck);
        validDuckDTO = duckMapper.mapToDTO(validDuck);

        ducks.add(validDuck);
        duckDTOs.add(validDuckDTO);
    }

    @Test
    void readAllDucksTest() {
        List<DuckDTO> ducksInDb = duckService.readAllDucks();
        assertThat(duckDTOs).isEqualTo(ducksInDb);
    }

    @Test
    void readByIdTest() {
        assertThat(validDuckDTO).isEqualTo(duckService.readById(validDuck.getId()));
    }

    @Test
    void createDuck() {
        Duck newDuck = new Duck(6, "george", "red", "beach", 6);
        assertThat(duckMapper.mapToDTO(newDuck)).isEqualTo(duckService.createDuck(newDuck));
    }

    @Test
    void updateDuck() {
        assertThat(validDuckDTO).isEqualTo(duckService.updateDuck(validDuck.getId(), validDuck));
    }

    @Test
    void deleteDuck() {
        assertThat(duckService.deleteDuck(validDuck.getId())).isTrue();
    }

    @Test
    void readByNameTest() {
        assertThat(validDuckDTO).isEqualTo(duckService.readByName(validDuck.getName()));
    }

}