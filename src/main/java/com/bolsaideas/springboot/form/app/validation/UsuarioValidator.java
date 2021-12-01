package com.bolsaideas.springboot.form.app.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bolsaideas.springboot.form.app.models.domain.Usuario;


@Component
public class UsuarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Usuario usuario = (Usuario)  target; 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.usuario.nombre"); 
		//Es lo mismo que
		/*
		 if(usuario.getNombre().isEmpty()){
		 	erros.rejectValue("nombre","NotEmpty.usuario.nombre"); 
		 } 
		 * */
		if(!usuario.getIdentificador().matches("[0-9]{4}[/-][\\d]{3}[-][A-Z]{1}")) {
			errors.rejectValue("identificador","pattern.usuario.identificador"); 
		}
		
		
	}

}
