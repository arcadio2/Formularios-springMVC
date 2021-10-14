package com.bolsaideas.springboot.form.app.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsaideas.springboot.form.app.models.domain.Usuario;
import com.bolsaideas.springboot.form.app.validation.UsuarioValidator;

@Controller
//@RequestMapping("/app")
@SessionAttributes("usuario")
public class FormController {	
	
	@Autowired
	private UsuarioValidator validator; 
	//Poblar la validación una vez que se envian los datos del form  
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//REMPLAZA EL VBALIDADOR POR DEFECTO
		//binder.setValidator(validator);
		//Añade al Validador
		binder.addValidators(validator);
	}
	
	
	@GetMapping({"/form","","/"})
	public String form(Model model) {
		Usuario usuario = new Usuario(); 
		usuario.setApellido("López"); 
		usuario.setNombre("arca"); 
		usuario.setIdentificador("1234-123-l");
		model.addAttribute("titulo", "FORMULARIO"); 
		model.addAttribute("usuario", usuario); 
		return "form"; 
	}
	@PostMapping("/form")
	/*@ModelAttribute("user")*/
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {  /*Si el objeto tiene los mismos atributos que se reciben, se crea automaticamentw*/
			/*@RequestParam String username,
			@RequestParam String password,
			@RequestParam String email) {*/
		/*
		Usuario usuario = new Usuario(); 
		usuario.setUsername(username); 
		usuario.setEmail(email); 
		usuario.setPassword(password); */
		//  validator.validate(usuario, result);
		model.addAttribute("titulo", "Resultado del formulario"); 
		if(result.hasErrors()) {
			/*Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err ->{
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage())); 
			});   
			model.addAttribute("error",errores); */
			return "form"; 
		}
		model.addAttribute("usuario", usuario); 
		status.setComplete();
		return "resultado"; 
	}
	
	
}
