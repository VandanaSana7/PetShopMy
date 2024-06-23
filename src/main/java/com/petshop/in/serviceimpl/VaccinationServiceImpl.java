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
import com.petshop.in.exceptions.vaccinations.VaccinationIdNotFoundException;
import com.petshop.in.exceptions.vaccinations.VaccinationInputInvalidException;
import com.petshop.in.exceptions.vaccinations.VaccinationsNotFoundException;
import com.petshop.in.model.Vaccinations;
import com.petshop.in.repository.VaccinationsRepository;
import com.petshop.in.service.VaccinationService;

@Service
public class VaccinationServiceImpl implements VaccinationService {

	@Autowired
	VaccinationsRepository vaccinationRepo;
	
	@Override
	public List<Vaccinations> getAllVaccinations() throws VaccinationsNotFoundException {
		
		List<Vaccinations> vaccinations = vaccinationRepo.findAll();
		if(vaccinations.isEmpty())
		{
			throw new VaccinationsNotFoundException("Validation failed: No vaccinations found");
		}
		return vaccinations;
	}

	@Override
	public Vaccinations getVaccinationsByVaccinationId(int vaccination_id) throws VaccinationIdNotFoundException {
		
		try
		{
			Vaccinations vacn = vaccinationRepo.findById(vaccination_id).get();
			return vacn;
		}
		catch(NoSuchElementException e)
		{
			throw new VaccinationIdNotFoundException("Validation failed: No vaccination Id found");
		}
		
	}

	@Override
	public List<Vaccinations> getAllAvailableVaccinations() {
		
		return vaccinationRepo.findAllAvailable();
	}

	@Override
	public List<Vaccinations> getAllUnavailableVaccinations() {
		
		return vaccinationRepo.findAllUnavailable();
	}

	@Override
	public SuccessResponse addVaccinations(Vaccinations vaccination) throws VaccinationInputInvalidException, MismatchDataTypeException {
		
		try
		{
			if(!ValidationClass.validationInt(vaccination.getName()))
			{
				throw new MismatchDataTypeException("Name should be string");
			}
			if(!ValidationClass.validationInt(vaccination.getDescription()))
			{
				throw new MismatchDataTypeException("Description should be string");
			}
			if(!(vaccination.getAvailable() == 0 || vaccination.getAvailable() == 1))
			{
				throw new MismatchDataTypeException("Invalid value");
			}
			Vaccinations vacn = vaccinationRepo.save(vaccination);
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Vaccination added successfully\n"+vacn);
			s.setStatus(HttpStatus.ACCEPTED.toString());
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(Exception e)
		{
			throw new VaccinationInputInvalidException("Validation failed: Vaccination cannot be added");
		}
	}

	@Override
	public SuccessResponse updateVaccinationsByVaccinationId(int vaccination_id, Vaccinations vaccination) throws VaccinationIdNotFoundException, MismatchDataTypeException{
		
		try
		{
			Vaccinations existingVaccination = vaccinationRepo.findById(vaccination_id).get();
			if(existingVaccination != null)
			{
				if(!ValidationClass.validationInt(vaccination.getName()))
				{
					throw new MismatchDataTypeException("Name should be string");
				}
				if(!ValidationClass.validationInt(vaccination.getDescription()))
				{
					throw new MismatchDataTypeException("Description should be string");
				}
				if(!(vaccination.getAvailable() == 0 || vaccination.getAvailable() == 1))
				{
					throw new MismatchDataTypeException("Invalid value");
				}
				existingVaccination.setName(vaccination.getName());
				existingVaccination.setDescription(vaccination.getDescription());
				existingVaccination.setPrice(vaccination.getPrice());
				existingVaccination.setAvailable(vaccination.getAvailable());
			}
			vaccinationRepo.save(existingVaccination);
			SuccessResponse s = new SuccessResponse();
			s.setMessage("Vaccination updated successfully\n"+existingVaccination);
			s.setStatus(HttpStatus.ACCEPTED.toString());
			s.setTimeStamp(LocalDate.now());
			return s;
		}
		catch(NoSuchElementException e)
		{
			throw new VaccinationIdNotFoundException("Validation failed: Vaccination Id not found");
		}
	}

}
