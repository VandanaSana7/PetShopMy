package com.petshop.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.suppliers.SupplierCityNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierIdNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierInputInvalidException;
import com.petshop.in.exceptions.suppliers.SupplierNameNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierStateNotFoundException;
import com.petshop.in.exceptions.suppliers.SuppliersNotFoundException;
import com.petshop.in.model.Suppliers;
//import com.petshop.in.service.SuppliersService;
import com.petshop.in.serviceimpl.SuppliersServiceImpl;

@RestController
@RequestMapping("/api/v1/suppliers")
public class SuppliersController {
	
	@Autowired
	SuppliersServiceImpl suppliersService;
	
	@GetMapping
	public ResponseEntity<List<Suppliers>> getAllSuppliers() throws SuppliersNotFoundException
	{
		return new ResponseEntity<>(suppliersService.getAllSuppliers(), HttpStatus.OK);
	}
	
	@GetMapping("/{supplier_id}")
	public ResponseEntity<Suppliers> getSuppliersBySupplierId(@PathVariable("supplier_id") int supplier_id) throws SupplierIdNotFoundException
	{
		return new ResponseEntity<>(suppliersService.getSuppliersBySupplierId(supplier_id), HttpStatus.OK);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<Suppliers>> getSuppliersByName(@PathVariable("name") String name) throws SupplierNameNotFoundException
	{
		return new ResponseEntity<>(suppliersService.getSuppliersByName(name), HttpStatus.OK);
	}
	
	@GetMapping("/city/{city_name}")
	public ResponseEntity<List<Suppliers>> getSuppliersByCityName(@PathVariable("city_name") String city_name) throws SupplierCityNotFoundException
	{
		return new ResponseEntity<>(suppliersService.getSuppliersByCityName(city_name), HttpStatus.OK);
	}
	
	@GetMapping("/state/{state_name}")
	public ResponseEntity<List<Suppliers>> getSuppliersByStateName(@PathVariable("state_name") String state_name) throws SupplierStateNotFoundException
	{
		return new ResponseEntity<>(suppliersService.getSuppliersByStateName(state_name), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<SuccessResponse> addSuppliers(@RequestBody Suppliers supplier) throws SupplierInputInvalidException, MismatchDataTypeException
	{
		return new ResponseEntity<>(suppliersService.addSuppliers(supplier), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{supplier_id}")
	public ResponseEntity<SuccessResponse> updateSuppliersBySupplierId(@PathVariable("supplier_id") int supplier_id, @RequestBody Suppliers supplier) throws SupplierIdNotFoundException, MismatchDataTypeException
	{
		return new ResponseEntity<>(suppliersService.updateSuppliersBySupplierId(supplier_id, supplier), HttpStatus.OK);
	}
	
	
}
