package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.ValidationClass;
import com.petshop.in.exceptions.addresses.AddressIdNotFoundException;
import com.petshop.in.exceptions.addresses.AddressInputInvalidException;
import com.petshop.in.exceptions.addresses.AddressNotFoundException;
import com.petshop.in.model.Addresses;
import com.petshop.in.repository.AddressesRepository;
import com.petshop.in.service.AddressesService;

@Service
public class AddressesServiceImpl implements AddressesService {

	@Autowired
	AddressesRepository addressRepo;
	
	@Override
	public List<Addresses> getAllAddresses() throws AddressNotFoundException {
		
		List<Addresses> addresses = addressRepo.findAll();
		if(addresses.isEmpty())
		{
			throw new AddressNotFoundException("Validation failed: No address found(List empty) ");
		}
		return addresses;
	}

	@Override
	public Addresses getAddressByAddressId(int address_id) throws AddressIdNotFoundException {
		
		try
		{
			Addresses addresses = addressRepo.findById(address_id).get();
			return addresses;
		}
		catch(NoSuchElementException e)
		{
			throw new AddressIdNotFoundException("Validation failed: No address found with this Id "+ address_id);
		}
		
	}

	@Override
	public SuccessResponse addAddress(Addresses address) throws AddressInputInvalidException, MismatchDataTypeException {
		
		try
		{
			if(!ValidationClass.validationInt(address.getStreet()))
			{
				throw new MismatchDataTypeException("Street should be string");
			}
			if(!ValidationClass.validationInt(address.getCity()))
			{
				throw new MismatchDataTypeException("City should be string");
			}
			if(!ValidationClass.validationInt(address.getState()))
			{
				throw new MismatchDataTypeException("State should be string");
			}
			if(ValidationClass.validationInt(address.getZipCode()))
			{
				throw new MismatchDataTypeException("Zipcode should not be string");
			}
			Addresses addr = addressRepo.save(address);
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Address added successfully\n"+"\n"+addr);;
			s.setStatus(HttpStatus.ACCEPTED.toString());
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(NoSuchElementException e)
		{
			throw new AddressInputInvalidException("Validation failed: Address is not valid");
		}
	}

	@Override
	public SuccessResponse updateAddressByAddressId(int address_id, Addresses address) throws AddressIdNotFoundException, MismatchDataTypeException {
		
		try
		{
			if(!ValidationClass.validationInt(address.getStreet()))
			{
				throw new MismatchDataTypeException("Street should be string");
			}
			if(!ValidationClass.validationInt(address.getCity()))
			{
				throw new MismatchDataTypeException("City should be string");
			}
			if(!ValidationClass.validationInt(address.getState()))
			{
				throw new MismatchDataTypeException("State should be string");
			}
			if(ValidationClass.validationInt(address.getZipCode()))
			{
				throw new MismatchDataTypeException("Zipcode should not be string");
			}
			if(address.getStreet().isEmpty())
			{
				throw new MismatchDataTypeException("Street should not be null. {Existing data should not removed}");
			}
			if(address.getCity().isEmpty())
			{
				throw new MismatchDataTypeException("City should not be null. {Existing data should not removed}");
			}
			if(address.getState().isEmpty())
			{
				throw new MismatchDataTypeException("State should not be null. {Existing data should not removed}");
			}
			if(address.getZipCode().isEmpty())
			{
				throw new MismatchDataTypeException("ZipCode should not be null. {Existing data should not removed}");
			}
			Addresses existingAddress = addressRepo.findById(address_id).get();
			if(existingAddress != null)
			{
				existingAddress.setStreet(address.getStreet());
				existingAddress.setCity(address.getCity());
				existingAddress.setState(address.getState());
				existingAddress.setZipCode(address.getZipCode());
			}
			addressRepo.save(existingAddress);
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Address updated successfully\n"+existingAddress);
			s.setStatus(HttpStatus.ACCEPTED.toString());
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(NoSuchElementException e)
		{
			throw new AddressIdNotFoundException("Validation failed: {Inavalid Id}"); 
		}
	}

}
