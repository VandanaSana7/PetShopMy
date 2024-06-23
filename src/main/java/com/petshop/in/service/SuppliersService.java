package com.petshop.in.service;

import java.util.List;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.suppliers.SupplierCityNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierIdNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierInputInvalidException;
import com.petshop.in.exceptions.suppliers.SupplierNameNotFoundException;
import com.petshop.in.exceptions.suppliers.SupplierStateNotFoundException;
import com.petshop.in.exceptions.suppliers.SuppliersNotFoundException;
import com.petshop.in.model.Suppliers;

public interface SuppliersService {
	
	List<Suppliers> getAllSuppliers() throws SuppliersNotFoundException;
	Suppliers getSuppliersBySupplierId(int supplier_id) throws SupplierIdNotFoundException;
	List<Suppliers> getSuppliersByName(String name) throws SupplierNameNotFoundException;
	List<Suppliers> getSuppliersByCityName(String city) throws SupplierCityNotFoundException;
	List<Suppliers> getSuppliersByStateName(String state) throws SupplierStateNotFoundException;
	SuccessResponse addSuppliers(Suppliers supplier) throws SupplierInputInvalidException, MismatchDataTypeException;
	SuccessResponse updateSuppliersBySupplierId(int supplier_id, Suppliers supplier) throws SupplierIdNotFoundException, MismatchDataTypeException;
	
}
