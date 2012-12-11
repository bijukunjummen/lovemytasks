package org.bk.lmt.registration;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.bk.lmt.types.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/registrations")
public class RegistrationController {
	
	@Resource Validator registrationValidator;
	
	@RequestMapping(params="form", produces="text/html")
	public String createForm(Model model){
		populateEditForm(model, new Registration());
		return "registrations/create";
	}
	
	@RequestMapping(method=RequestMethod.POST, produces="text/html")
	public String create(@Valid Registration registration, BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()){
			populateEditForm(model, registration);
			return "registrations/create";
		}
		model.asMap().clear();
        //TODO: persist registration
		return "redirect:/registrations/successful";
	}
	
	@RequestMapping(value="/successful", produces="text/html")
	public String successful(){
		return "registrations/successful";
	}
	
	private void populateEditForm(Model model, Registration registration){
		model.addAttribute("registration", registration);
	}
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(registrationValidator);
	}
}
