package com.bolsaideas.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
//import java.util.HashMap;
//import java.util.Map;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsaideas.springboot.form.app.editors.NombreMayusculaEditor;
import com.bolsaideas.springboot.form.app.editors.PaisPropertyEditor;
import com.bolsaideas.springboot.form.app.editors.RolesEditor;
import com.bolsaideas.springboot.form.app.models.domain.Pais;
import com.bolsaideas.springboot.form.app.models.domain.Rol;
import com.bolsaideas.springboot.form.app.models.domain.Usuario;
import com.bolsaideas.springboot.form.app.services.PaisService;
import com.bolsaideas.springboot.form.app.services.RolService;
import com.bolsaideas.springboot.form.app.validation.UsuarioValidator;

@Controller
//@RequestMapping("/app")
@SessionAttributes("usuario")
public class FormController {	
	
	@Autowired
	private UsuarioValidator validator; 
	
	@Autowired
	private PaisService paisService; 
	
	@Autowired 
	private RolService rolService; 
	
	@Autowired
	private PaisPropertyEditor paisEditor; 
	
	@Autowired
	RolesEditor roleEditor;
	
	//Poblar la validación una vez que se envian los datos del form  
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//REMPLAZA EL VBALIDADOR POR DEFECTO
		//binder.setValidator(validator);
		//Añade al Validador
		binder.addValidators(validator);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		dateformat.setLenient(false); //es estreicto o tolerante
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateformat,false));
		binder.registerCustomEditor(String.class,"nombre", new NombreMayusculaEditor());
		//binder.registerCustomEditor(String.class,"email", new NombreMayusculaEditor());
	
		binder.registerCustomEditor(Pais.class,"pais", paisEditor);
		binder.registerCustomEditor(Rol.class,"roles", roleEditor);
		
	}
	
	
	@GetMapping({"/form","","/"})
	public String form(Model model) {
		Usuario usuario = new Usuario(); 
		usuario.setApellido("López"); 
		usuario.setNombre("arca"); 
		usuario.setHabilitar(true); 
		usuario.setIdentificador("1234-123-L");
		model.addAttribute("titulo", "FORMULARIO"); 
		
		model.addAttribute("usuario", usuario); 
		
		return "form"; 
	}
	
	
	@ModelAttribute("paises")
	public List<String> paises(){
		return Arrays.asList("España","México","Chile","Argentina","Perú");
	}
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises(){
		return paisService.listar();
	}
	
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap(){
		Map <String,String> paises = new HashMap<String,String>();
		paises.put("ES", "España"); 
		paises.put("MX", "México"); 
		paises.put("CH", "Chile"); 
		paises.put("PE", "Perú"); 
		paises.put("CO", "Colombia"); 
		paises.put("VE", "Venezuela"); 
		paises.put("USA", "Estados unidos"); 
		return  paises;
	}

	@ModelAttribute("lostaRolesMap")
	public Map<String, String> listaRolesMap(){
		Map <String,String> roles = new HashMap<String,String>();
		roles.put("ROLE_ADMIN", "Administrador"); 
		roles.put("ROLE_USER", "Usuario"); 
		roles.put("ROLE_MODERATOR", "Moderador"); 
		
		return  roles;
	}
	@ModelAttribute("listaRoles")
	public List<Rol> listaRoles(){
		return this.rolService.listar(); 
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listarolesString(){
		List<String> roles = new ArrayList();
		roles.add("ROLE_ADMIN"); 
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR"); 
		
		return roles; 
	}
	
	@PostMapping("/form")
	/*@ModelAttribute("user")*/
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {  /*Si el objeto tiene los mismos atributos que se reciben, se crea automaticamentw*/

		//System.out.println(result.getFieldValue("pais"));
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
			//System.out.println(result.getFieldErrors().get(0).getField());
			
			return "form"; 
		}
		
		model.addAttribute("usuario", usuario); 
		status.setComplete();
		return "resultado"; 
	}
	
	
}
