package com.example.duckdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.data.repository.DuckRepository;
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.exceptions.DuckNotFoundException;
import com.example.duckdemo.exceptions.FunnyDuckNotFoundException;
import com.example.duckdemo.mappers.DuckMapper;

@Service
public class DuckService {

    private DuckRepository duckRepository;
    private DuckMapper duckMapper;

    @Autowired
    public DuckService(DuckRepository duckRepository, DuckMapper duckMapper) {
        this.duckRepository = duckRepository;
        this.duckMapper = duckMapper;
    }

    public List<DuckDTO> readAllDucks() {
        List<Duck> ducks = duckRepository.findAll();
        List<DuckDTO> duckDTOs = new ArrayList<>();
        ducks.forEach(duck -> duckDTOs.add(duckMapper.mapToDTO(duck)));

        return duckDTOs;
    }

    public DuckDTO readById(int id) {
        Optional<Duck> duck = duckRepository.findById(id);
        if (duck.isPresent()) {
            return duckMapper.mapToDTO(duck.get());
        } else {
            throw new DuckNotFoundException("Duck not found, QUACK!");
        }
    }

    public DuckDTO createDuck(Duck duck) {
        return duckMapper.mapToDTO(duckRepository.save(duck));
    }

    public DuckDTO updateDuck(int id, Duck duck) {
        Optional<Duck> optional = duckRepository.findById(id);
        if (optional.isPresent()) {
            Duck duckToUpdate = optional.get();
            duckToUpdate.setName(duck.getName());
            duckToUpdate.setColour(duck.getColour());
            duckToUpdate.setHabitat(duck.getHabitat());
            duckToUpdate.setAge(duck.getAge());
            return duckMapper.mapToDTO(duckRepository.save(duckToUpdate));
        } else {
            throw new FunnyDuckNotFoundException();
        }
    }

    public boolean deleteDuck(int id) {
        duckRepository.deleteById(id);
        return true;
    }

    public DuckDTO readByName(String name) {
        return duckMapper.mapToDTO(duckRepository.findByName(name));
    }
}
