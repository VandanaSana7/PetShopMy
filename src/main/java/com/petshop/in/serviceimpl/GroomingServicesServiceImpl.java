package com.petshop.in.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.ValidationClass;
import com.petshop.in.exceptions.groomingservices.ServiceAvailableException;
import com.petshop.in.exceptions.groomingservices.ServiceIdNotFoundException;
import com.petshop.in.exceptions.groomingservices.ServiceInvalidInputException;
import com.petshop.in.exceptions.groomingservices.ServiceUnavailableException;
import com.petshop.in.exceptions.groomingservices.ServicesNotFoundException;
import com.petshop.in.model.GroomingServices;
import com.petshop.in.repository.GroomingServicesRepository;
import com.petshop.in.service.GroomingServicesService;

@Service
public class GroomingServicesServiceImpl implements GroomingServicesService{

	@Autowired
	GroomingServicesRepository groomingServiceRepo;
	
	@Override
	public List<GroomingServices> getAllGroomingServices() throws ServicesNotFoundException {
		
		List<GroomingServices> gr_services = groomingServiceRepo.findAll();
		
		if(gr_services.isEmpty())
		{
			throw new ServicesNotFoundException("Validation failed: No grooming services");
		}
		
		return gr_services;
	}

	@Override
	public GroomingServices getGroomingServicesByServiceId(int service_id) throws ServiceIdNotFoundException {
		
		try
		{
			GroomingServices gr_service = groomingServiceRepo.findById(service_id).get();
			return gr_service;
		}
		catch(NoSuchElementException e)
		{
			throw new ServiceIdNotFoundException("Validation failed: Service Id "+service_id+" not found");
		}
		
	}

	@Override
	public List<GroomingServices> getAllAvailableGroomingServices() throws ServiceAvailableException{
		
		List<GroomingServices> gr_services = groomingServiceRepo.findAllAvailable();
		if(gr_services.isEmpty())
		{
			throw new ServiceAvailableException("Validation failed: No grooming services available");
		}
		return gr_services;
	}

	@Override
	public List<GroomingServices> getAllUnavailableGroomingServices() throws ServiceUnavailableException {
		
		List<GroomingServices> gr_services = groomingServiceRepo.findAllUnavailable();
		if(gr_services.isEmpty())
		{
			throw new ServiceUnavailableException("Validation failed: No grooming services available");
		}
		return gr_services;
	}

	@Override
	public SuccessResponse addGroomingServices(GroomingServices service) throws ServiceInvalidInputException, MismatchDataTypeException{
		
		try
		{
			if(!ValidationClass.validationInt(service.getName()))
			{
				throw new MismatchDataTypeException("Name should be string");
			}
			if(!ValidationClass.validationInt(service.getDescription()))
			{
				throw new MismatchDataTypeException("Description should be string");
			}
			if(!(service.getAvailable() == 0 || service.getAvailable() == 1))
			{
				throw new MismatchDataTypeException("Invalid available status");
			}
			GroomingServices gr_service = groomingServiceRepo.save(service);
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Data added "+gr_service);
			s.setStatus("Success");
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(Exception e)
		{
			throw new ServiceInvalidInputException("Validation failed: Grooming services cannot be added");
		}
		
	}

	@Override
	public SuccessResponse updateGroomingServicesByServiceId(GroomingServices service, int service_id) throws ServiceIdNotFoundException, MismatchDataTypeException{
		
		try
		{
			GroomingServices gr_service = groomingServiceRepo.findById(service_id).get();
			if(gr_service != null)
			{
				if(!ValidationClass.validationInt(service.getName()))
				{
					throw new MismatchDataTypeException("Name should be string");
				}
				if(!ValidationClass.validationInt(service.getDescription()))
				{
					throw new MismatchDataTypeException("Description should be string");
				}
				if(!(service.getAvailable() == 0 || service.getAvailable() == 1))
				{
					throw new MismatchDataTypeException("Invalid available status");
				}
				gr_service.setName(service.getName());
				gr_service.setDescription(service.getDescription());
				gr_service.setPrice(service.getPrice());
				gr_service.setAvailable(service.getAvailable());
				groomingServiceRepo.save(gr_service);
			}
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Data updated "+gr_service);
			s.setStatus("Success");
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(NoSuchElementException e)
		{
			throw new ServiceIdNotFoundException("Validation failed: No id found");
		}
	}

}
