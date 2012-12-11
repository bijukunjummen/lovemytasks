package org.bk.lmt.registration;

import javax.annotation.Resource;

import org.bk.lmt.types.Registration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class RegistrationValidator implements Validator {

	@Resource private Validator jsr303Validator;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Registration.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		jsr303Validator.validate(target, errors);
		if (!errors.hasErrors()){
			Registration registration = (Registration)target;
			if (!registration.getPassword().equals(registration.getConfirmPassword())){
				errors.rejectValue("confirmPassword", "registration.passwordsNoMatch");
			}
		}
	}

}
