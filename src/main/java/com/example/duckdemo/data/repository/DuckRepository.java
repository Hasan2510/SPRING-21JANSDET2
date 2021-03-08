package com.example.duckdemo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.duckdemo.data.model.Duck;

@Repository
public interface DuckRepository extends JpaRepository<Duck, Integer> {

}
