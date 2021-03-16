package com.example.duckdemo.data.repository;

import com.example.duckdemo.data.model.Pond;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PondRepository extends JpaRepository<Pond, Integer> {

}
