package com.petshop.in.service;

import java.util.List;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.vaccinations.VaccinationIdNotFoundException;
import com.petshop.in.exceptions.vaccinations.VaccinationInputInvalidException;
import com.petshop.in.exceptions.vaccinations.VaccinationsNotFoundException;
import com.petshop.in.model.Vaccinations;

public interface VaccinationService {
	
	List<Vaccinations> getAllVaccinations() throws VaccinationsNotFoundException;
	Vaccinations getVaccinationsByVaccinationId(int vaccination_id) throws VaccinationIdNotFoundException;
	List<Vaccinations> getAllAvailableVaccinations();
	List<Vaccinations> getAllUnavailableVaccinations();
	SuccessResponse addVaccinations(Vaccinations vaccination) throws VaccinationInputInvalidException, MismatchDataTypeException;
	SuccessResponse updateVaccinationsByVaccinationId(int vaccination_id, Vaccinations vaccination) throws VaccinationIdNotFoundException, MismatchDataTypeException;
	
}
