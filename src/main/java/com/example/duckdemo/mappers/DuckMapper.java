package com.example.duckdemo.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.duckdemo.data.model.Duck;
import com.example.duckdemo.dto.DuckDTO;

@Component
public class DuckMapper {

	private ModelMapper modelMapper;
	
	@Autowired
	public DuckMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public DuckDTO mapToDTO(Duck duck) {
		return this.modelMapper.map(duck, DuckDTO.class);
	}
	
	public Duck mapToDuck(DuckDTO duckDTO) {
		return this.modelMapper.map(duckDTO, Duck.class);
	}
}
