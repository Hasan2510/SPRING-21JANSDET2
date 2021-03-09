package com.example.duckdemo.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.duckdemo.data.model.Duck;

@Repository
public interface DuckRepository extends JpaRepository<Duck, Integer> {

	// DERIVED QUERY
	// Derived queries are derived from their method names
	// - Spring Data JPA converts method name to query
	// - findBy = SELECT * FROM
	// - https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	public Duck findByName(String name);
	
	
	// NATIVE QUERY
	// Native queries use a specific SQL vendors syntax (Such as MySQL 5 or MySQL 8 or InnoMySQL)
	// - We use the @Query annotation to write a native query
	// - Native queries require nativeQuery set to true to indicate we are using pure SQL and not JPQL
	@Query(value = "SELECT * FROM duck", nativeQuery = true)
	public List<Duck> getAllDucksSQL();
	
	
	// JPQL
	// - Java Persistence Query Language
	// - Java's own query language, it is database-independent.
	// - Can use objects in our queries
	@Query("SELECT d FROM Duck d")
	public List<Duck> getAllDucksJPQL();
	
	
	// JPQL and Native Queries can be passed parameters in the same way
	// - Uses '?<num>' syntax to select the corresponding parameter from the method parameters
	@Query("SELECT d FROM Duck d WHERE d.name = ?1")
	public Duck getDuckByNameJPQL(String name);
}
