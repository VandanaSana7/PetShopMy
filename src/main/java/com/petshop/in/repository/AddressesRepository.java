package com.petshop.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petshop.in.model.Addresses;

@Repository
public interface AddressesRepository extends JpaRepository<Addresses, Integer> {

}
