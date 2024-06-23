package com.petshop.in.service;

import java.util.List;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.groomingservices.ServiceAvailableException;
import com.petshop.in.exceptions.groomingservices.ServiceIdNotFoundException;
import com.petshop.in.exceptions.groomingservices.ServiceInvalidInputException;
import com.petshop.in.exceptions.groomingservices.ServiceUnavailableException;
import com.petshop.in.exceptions.groomingservices.ServicesNotFoundException;
import com.petshop.in.model.GroomingServices;

public interface GroomingServicesService {
	
	List<GroomingServices> getAllGroomingServices() throws ServicesNotFoundException;
	GroomingServices getGroomingServicesByServiceId(int service_id) throws ServiceIdNotFoundException;
	List<GroomingServices> getAllAvailableGroomingServices()throws ServiceAvailableException;
	List<GroomingServices> getAllUnavailableGroomingServices() throws ServiceUnavailableException;
	SuccessResponse addGroomingServices(GroomingServices service) throws ServiceInvalidInputException, MismatchDataTypeException;
	SuccessResponse updateGroomingServicesByServiceId(GroomingServices service, int service_id) throws ServiceIdNotFoundException, MismatchDataTypeException;
	
}
