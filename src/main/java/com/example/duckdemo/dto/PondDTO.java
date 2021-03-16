package com.example.duckdemo.dto;

import java.util.List;
import java.util.Objects;

public class PondDTO {

    private int id;

    private String name;

    private List<DuckDTO> ducks;

    public PondDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PondDTO() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<DuckDTO> getDucks() {
        return this.ducks;
    }

    public void setDucks(List<DuckDTO> ducks) {
        this.ducks = ducks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PondDTO)) {
            return false;
        }
        PondDTO pondDTO = (PondDTO) o;
        return id == pondDTO.id && Objects.equals(name, pondDTO.name) && Objects.equals(ducks, pondDTO.ducks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ducks);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", ducks='" + getDucks() + "'" + "}";
    }

}
