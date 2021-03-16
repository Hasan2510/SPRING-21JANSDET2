package com.example.duckdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.duckdemo.data.model.Pond;
import com.example.duckdemo.data.repository.PondRepository;
import com.example.duckdemo.dto.PondDTO;
import com.example.duckdemo.exceptions.PondNotFoundException;
import com.example.duckdemo.mappers.PondMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PondService {

    private PondRepository pondRepository;
    private PondMapper pondMapper;

    @Autowired
    public PondService(PondRepository pondRepository, PondMapper pondMapper) {
        this.pondRepository = pondRepository;
        this.pondMapper = pondMapper;
    }

    public List<PondDTO> readAllPonds() {
        List<Pond> ponds = pondRepository.findAll();
        List<PondDTO> pondDTOs = new ArrayList<>();
        ponds.forEach(pond -> pondDTOs.add(pondMapper.mapToDTO(pond)));
        return pondDTOs;
    }

    public PondDTO createPond(Pond pond) {
        return pondMapper.mapToDTO(pondRepository.save(pond));
    }

    public Boolean deletePond(int id) {
        if (pondRepository.existsById(id)) {
            pondRepository.deleteById(id);
        } else {
            throw new PondNotFoundException("The pond wasn't found");
        }
        return !pondRepository.existsById(id);
    }

    public PondDTO readById(int id) {
        Optional<Pond> pond = pondRepository.findById(id);
        if (pond.isPresent()) {
            return pondMapper.mapToDTO(pond.get());
        } else {
            throw new PondNotFoundException("Pond not found");
        }
    }

    public PondDTO updatePond(int id, Pond pond) {
        Optional<Pond> optional = pondRepository.findById(id);
        if (optional.isPresent()) {
            Pond pondToUpdate = optional.get();
            pondToUpdate.setName(pond.getName());
            return pondMapper.mapToDTO(pondRepository.save(pondToUpdate));
        } else {
            throw new PondNotFoundException();
        }
    }
}