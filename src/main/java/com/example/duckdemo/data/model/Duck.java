package com.example.duckdemo.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

// Duck is our Business Domain

@Entity // JPA + Hibernate will auto-create table for this class
public class Duck {

	// Validation Rules specify what can and can't be processed in our DB
	
	@Id // Auto-incrementing
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name", unique = true)
	@NotNull
	private String name;
	
	@NotNull
	private String colour;
	
	@NotNull
	private String habitat;
	
	@Min(0)
	@Max(36)
	private int age;
	
	public Duck() {
		
	}
	
	public Duck(String name, String colour, String habitat, int age) {
		super();
		this.name = name;
		this.colour = colour;
		this.habitat = habitat;
		this.age = age;
	}
	
	public Duck(int id, String name, String colour, String habitat, int age) {
		super();
		this.id = id;
		this.name = name;
		this.colour = colour;
		this.habitat = habitat;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
		result = prime * result + ((habitat == null) ? 0 : habitat.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Duck other = (Duck) obj;
		if (age != other.age)
			return false;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		if (habitat == null) {
			if (other.habitat != null)
				return false;
		} else if (!habitat.equals(other.habitat))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
