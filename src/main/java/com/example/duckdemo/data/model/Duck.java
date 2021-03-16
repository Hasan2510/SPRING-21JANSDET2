package com.example.duckdemo.data.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Duck {

    @Id
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

    @ManyToOne(targetEntity = Pond.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_pond_id")
    private Pond pond;

    public Duck() {
    }

    public Duck(int id, String name, String colour, String habitat, int age) {
        this.id = id;
        this.name = name;
        this.colour = colour;
        this.habitat = habitat;
        this.age = age;
    }

    public Duck(String name, String colour, String habitat, int age) {
        this.name = name;
        this.colour = colour;
        this.habitat = habitat;
        this.age = age;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColour() {
        return this.colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getHabitat() {
        return this.habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", colour='" + getColour() + "'"
                + ", habitat='" + getHabitat() + "'" + ", age='" + getAge() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Duck)) {
            return false;
        }
        Duck duck = (Duck) o;
        return id == duck.id && Objects.equals(name, duck.name) && Objects.equals(colour, duck.colour)
                && Objects.equals(habitat, duck.habitat) && age == duck.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, colour, habitat, age);
    }

    public Pond getPond() {
        return this.pond;
    }

    public void setPond(Pond pond) {
        this.pond = pond;
    }

}
