package com.petshop.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petshop.in.model.GroomingServices;

@Repository
public interface GroomingServicesRepository extends JpaRepository<GroomingServices, Integer> {
	
	@Query("select g from GroomingServices g where g.available = 1")
	List<GroomingServices> findAllAvailable();
	
	@Query("select g from GroomingServices g where g.available = 0")
	List<GroomingServices> findAllUnavailable();
	
}
