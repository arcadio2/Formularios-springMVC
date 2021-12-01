package com.bolsaideas.springboot.form.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IndentificadorRegexValidador  implements ConstraintValidator<IndetificadorRegex, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.matches("[0-9]{4}[/-][\\d]{3}[-][A-Z]{1}")) {
			return true; 
		}
		return false;
	}

}
