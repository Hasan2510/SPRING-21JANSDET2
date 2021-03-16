package com.example.duckdemo.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.data.repository.DuckRepository;
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.mappers.DuckMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class DuckServiceUnitTest {

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
    void readAllDucksTest() {
        when(duckRepository.findAll()).thenReturn(ducks);
        when(duckMapper.mapToDTO(Mockito.any(Duck.class))).thenReturn(validDuckDTO);

        assertThat(duckDTOs).isEqualTo(duckService.readAllDucks());

        verify(duckRepository, times(1)).findAll();
        verify(duckMapper, times(1)).mapToDTO(Mockito.any(Duck.class));
    }

    @Test
    void readByIdTest() {
        Optional<Duck> optional = Optional.of(validDuck);
        when(duckRepository.findById(Mockito.any(Integer.class))).thenReturn(optional);
        when(duckMapper.mapToDTO(Mockito.any(Duck.class))).thenReturn(validDuckDTO);

        assertThat(validDuckDTO).isEqualTo(duckService.readById(validDuck.getId()));

        verify(duckRepository, times(1)).findById(Mockito.any(Integer.class));
        verify(duckMapper, times(1)).mapToDTO(Mockito.any(Duck.class));
    }

    @Test
    void createDuckTest() {
        when(duckRepository.save(Mockito.any(Duck.class))).thenReturn(validDuck);
        when(duckMapper.mapToDTO(Mockito.any(Duck.class))).thenReturn(validDuckDTO);

        assertThat(validDuckDTO).isEqualTo(duckService.createDuck(validDuck));

        verify(duckRepository, times(1)).save(Mockito.any(Duck.class));
        verify(duckMapper, times(1)).mapToDTO(Mockito.any(Duck.class));
    }

    @Test
    void updateDuckTest() {
        Optional<Duck> optional = Optional.of(validDuck);
        when(duckRepository.findById(Mockito.any(Integer.class))).thenReturn(optional);
        when(duckRepository.save(Mockito.any(Duck.class))).thenReturn(validDuck);
        when(duckMapper.mapToDTO(Mockito.any(Duck.class))).thenReturn(validDuckDTO);

        assertThat(validDuckDTO).isEqualTo(duckService.updateDuck(validDuck.getId(), validDuck));

        verify(duckRepository, times(1)).findById(Mockito.any(Integer.class));
        verify(duckRepository, times(1)).save(Mockito.any(Duck.class));
        verify(duckMapper, times(1)).mapToDTO(Mockito.any(Duck.class));
    }

    @Test
    void deleteDuckTest() {
        assertThat(duckService.deleteDuck(validDuck.getId())).isEqualTo(true);
        verify(duckRepository, times(1)).deleteById(Mockito.any(Integer.class));
    }

    @Test
    void readByNameTest() {
        when(duckRepository.findByName(Mockito.any(String.class))).thenReturn(validDuck);
        when(duckMapper.mapToDTO(Mockito.any(Duck.class))).thenReturn(validDuckDTO);

        assertThat(validDuckDTO).isEqualTo(duckService.readByName(validDuck.getName()));

        verify(duckRepository, times(1)).findByName(Mockito.any(String.class));
        verify(duckMapper, times(1)).mapToDTO(Mockito.any(Duck.class));
    }
}
