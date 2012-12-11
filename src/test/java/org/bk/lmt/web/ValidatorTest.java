package org.bk.lmt.web;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.bk.lmt.types.Registration;
import org.hibernate.validator.HibernateValidator;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ValidatorTest {

	@Test
	public void testValidator() {
		Configuration configuration = Validation.byProvider(HibernateValidator.class).configure();
		ValidatorFactory validatorFactory = configuration.buildValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		
		Registration registration = new Registration();
		registration.setPassword("ab");
		Set<ConstraintViolation<Registration>> violations =  validator.validate(registration);
		System.out.println(violations);
	}

}
