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
import com.petshop.in.exceptions.addresses.AddressIdNotFoundException;
import com.petshop.in.exceptions.addresses.AddressInputInvalidException;
import com.petshop.in.exceptions.addresses.AddressNotFoundException;
import com.petshop.in.model.Addresses;
import com.petshop.in.serviceimpl.AddressesServiceImpl;

@RestController
@RequestMapping("/api/v1/address")
public class AddressesController {

	@Autowired
	AddressesServiceImpl addressService;
	
	@GetMapping
	public ResponseEntity<List<Addresses>> getAllAddresses() throws AddressNotFoundException
	{
		return new ResponseEntity<>(addressService.getAllAddresses(), HttpStatus.OK);
	}
	
	@GetMapping("/{address_id}")
	public ResponseEntity<Addresses> getAddressByAddressId(@PathVariable("address_id") int address_id) throws AddressIdNotFoundException
	{
		return new ResponseEntity<>(addressService.getAddressByAddressId(address_id), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<SuccessResponse> addAddress(@RequestBody Addresses address) throws AddressInputInvalidException, MismatchDataTypeException
	{
		return new ResponseEntity<>(addressService.addAddress(address), HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{address_id}")
	public ResponseEntity<SuccessResponse> updateAddressByAddressId(@PathVariable("address_id") int address_id, @RequestBody Addresses address) throws AddressIdNotFoundException, MismatchDataTypeException
	{
		return new ResponseEntity<>(addressService.updateAddressByAddressId(address_id, address), HttpStatus.OK);
	}
}
