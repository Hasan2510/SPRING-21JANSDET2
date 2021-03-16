package com.example.duckdemo.data.repository;

import java.util.List;

import com.example.duckdemo.data.model.Duck;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DuckRepository extends JpaRepository<Duck, Integer> {

    public Duck findByName(String name);

    @Query(value = "SELECT * FROM duck", nativeQuery = true)
    public List<Duck> getAllDucksSQL();

    @Query("SELECT d FROM Duck d WHERE d.colour = ?1")
    public List<Duck> getDucksByColourJPQL(String colour);
}
