package com.example.duckdemo.data.model;

import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "pond", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Duck> ducks;

    public Pond(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pond() {
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

    public List<Duck> getDucks() {
        return this.ducks;
    }

    public void setDucks(List<Duck> ducks) {
        this.ducks = ducks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pond)) {
            return false;
        }
        Pond pond = (Pond) o;
        return id == pond.id && Objects.equals(name, pond.name) && Objects.equals(ducks, pond.ducks);
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
