package com.example.duckdemo.data.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pond")
public class Pond {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pond_id")
	private int id;
	
	@Column(name = "name", unique = true)
	private String name;
	
	// one-to-many relationship
	// - does not own the relationship, a duck can change ponds :O
	// - mappedBy indicates the entity is the inverse (does not own the relationship)
	@OneToMany(mappedBy = "pond", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Duck> ducks;

	public Pond() {
		super();
	}

	public Pond(String name, List<Duck> ducks) {
		super();
		this.name = name;
		this.ducks = ducks;
	}

	public Pond(int id, String name, List<Duck> ducks) {
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

	public List<Duck> getDucks() {
		return ducks;
	}

	public void setDucks(List<Duck> ducks) {
		this.ducks = ducks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ducks == null) ? 0 : ducks.hashCode());
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
		Pond other = (Pond) obj;
		if (ducks == null) {
			if (other.ducks != null)
				return false;
		} else if (!ducks.equals(other.ducks))
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
