package com.example.duckdemo.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.data.model.Pond;
import com.example.duckdemo.dto.DuckDTO;
import com.example.duckdemo.dto.PondDTO;

@Component
public class PondMapper {

	private ModelMapper modelMapper;
	
	@Autowired
	public PondMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public PondDTO mapToDTO(Pond pond) {
		return this.modelMapper.map(pond, PondDTO.class);
	}
	
	public Pond mapToPond(PondDTO pondDTO) {
		return this.modelMapper.map(pondDTO, Pond.class);
	}
}
