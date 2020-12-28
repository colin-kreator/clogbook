package com.pilotair.clogbook.webapp.form.uniqueemail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.pilotair.clogbook.core.service.UserService;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	private UserService userService;

	@Override
	public boolean isValid( String value, ConstraintValidatorContext context ) {
		return value != null && !userService.isEmailAlreadyInUse( value );
	}

}