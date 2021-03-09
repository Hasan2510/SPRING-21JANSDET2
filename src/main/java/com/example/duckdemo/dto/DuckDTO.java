package com.example.duckdemo.dto;

// Simple POJO (Plain Old Java Object)
// - DTO is the Data Transfer Object that represents a sanitised version of our business domain model
public class DuckDTO {

	private int id;
	
	private String name;
	
	private String colour;
	
	private String habitat;
	
	public DuckDTO() {
		super();
	}

	public DuckDTO(int id, String name, String colour, String habitat) {
		super();
		this.id = id;
		this.name = name;
		this.colour = colour;
		this.habitat = habitat;
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

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}
	
	
}
