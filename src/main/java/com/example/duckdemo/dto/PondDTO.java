package com.example.duckdemo.dto;

import java.util.List;

public class PondDTO {

	private int id;
	
	private String name;
	
	private List<DuckDTO> ducks;
	
	public PondDTO() {
		
	}

	public PondDTO(int id, String name, List<DuckDTO> ducks) {
		super();
		this.id = id;
		this.name = name;
		this.ducks = ducks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DuckDTO> getDucks() {
		return ducks;
	}

	public void setDucks(List<DuckDTO> ducks) {
		this.ducks = ducks;
	}
}
