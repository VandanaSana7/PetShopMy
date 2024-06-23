package com.petshop.in.controller.thymeleaf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petshop.in.exceptions.MismatchDataTypeException;
import com.petshop.in.exceptions.SuccessResponse;
import com.petshop.in.exceptions.groomingservices.ServiceAvailableException;
import com.petshop.in.exceptions.groomingservices.ServiceIdNotFoundException;
import com.petshop.in.exceptions.groomingservices.ServiceInvalidInputException;
import com.petshop.in.exceptions.groomingservices.ServiceUnavailableException;
import com.petshop.in.exceptions.groomingservices.ServicesNotFoundException;
import com.petshop.in.model.GroomingServices;
import com.petshop.in.service.GroomingServicesService;

@Controller
@RequestMapping("/services")
public class GroomingServicesThymeleaf {

	@Autowired
	private GroomingServicesService groomingService;
	
//	@GetMapping("/home")
//	public String showHomePage()
//	{
//		return "HomePage";
//	}
	
	@GetMapping("/form")
	public String showServiceIdForm() {
		return "ServiceIdForm";
	}
	
	@GetMapping("/getAllServices")
	public String getAllGroomingServices(Model model) throws ServicesNotFoundException
	{
		List<GroomingServices> servicesList = groomingService.getAllGroomingServices();
		model.addAttribute("AllGroomingServices", servicesList);
		return "ServicesList";
	}
	
	@GetMapping("/getServiceId/serviceId")
	public String getServiceById(@RequestParam("serviceId") Integer serviceId, Model model) throws ServiceIdNotFoundException
	{
		GroomingServices service = groomingService.getGroomingServicesByServiceId(serviceId);
		model.addAttribute("ServiceById", service);
		return "ServiceById";
	}
	
	@GetMapping("/available")
	public String getAllAvailableServices(Model model) throws ServiceAvailableException
	{
		List<GroomingServices> availableServices = groomingService.getAllAvailableGroomingServices();
		model.addAttribute("AllAvailableServices", availableServices);
		return "AvailableGroomingServices";
	}
	
	@GetMapping("/unavailable")
	public String getAllUnavailableServices(Model model) throws ServiceUnavailableException
	{
		List<GroomingServices> unavailableServices = groomingService.getAllUnavailableGroomingServices();
		model.addAttribute("AllUnavailableServices", unavailableServices);
		return "UnavailableGroomingServices";
	}
	
	@GetMapping("/postserviceform")
	public String showPostServiceForm()
	{
		return "PostServiceForm";
	}
	
	@PostMapping("/add")
	public String addGroomingService(Model model, @ModelAttribute("service") GroomingServices service) throws ServiceInvalidInputException, MismatchDataTypeException
	{
		SuccessResponse s = groomingService.addGroomingServices(service);
		model.addAttribute("AddService", s);
		return "PostAddService";
	}
	
	@GetMapping("/updateidform")
	public String showPostUpdateServiceIdForm() {
		return "UpdateServiceIdForm";
	}

	@GetMapping("/updateserviceform")
	public String updateService(@RequestParam("serviceId") Integer serviceId, Model model) throws ServiceIdNotFoundException {
		GroomingServices service = groomingService.getGroomingServicesByServiceId(serviceId);
		model.addAttribute("Service", service);
		return "UpdateServiceForm";

	}

	@GetMapping("/updatedetails")
	public String updateGroomingService(Model model, @ModelAttribute("updateService") GroomingServices service, 
			@RequestParam("serviceId") Integer serviceId) throws ServiceIdNotFoundException, MismatchDataTypeException {
		SuccessResponse s = groomingService.updateGroomingServicesByServiceId(service, serviceId);
		model.addAttribute("UpdateService", s);

		return "UpdateService";

	}
	
	
	
}
