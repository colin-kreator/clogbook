package com.pilotair.clogbook.webapp.form.uniqueemail;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint( validatedBy = UniqueEmailValidator.class )
@Retention( RetentionPolicy.RUNTIME )
@Target( { ElementType.FIELD } )
public @interface UniqueEmail {

	String message() default "This email is already registered with CLogbook !";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}