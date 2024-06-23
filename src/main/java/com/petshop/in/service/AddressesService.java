package com.petshop.in.service;

import java.util.List;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.addresses.AddressIdNotFoundException;
import com.petshop.in.exceptions.addresses.AddressInputInvalidException;
import com.petshop.in.exceptions.addresses.AddressNotFoundException;
import com.petshop.in.model.Addresses;

public interface AddressesService {

	List<Addresses> getAllAddresses() throws AddressNotFoundException;
	Addresses getAddressByAddressId(int address_id) throws AddressIdNotFoundException;
	SuccessResponse addAddress(Addresses address) throws AddressInputInvalidException, MismatchDataTypeException;
	SuccessResponse updateAddressByAddressId(int address_id, Addresses address) throws AddressIdNotFoundException, MismatchDataTypeException;
	
}
