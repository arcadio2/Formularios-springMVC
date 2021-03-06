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
import org.springframework.web.bind.annotation.SessionAttribute;
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
	
	//Poblar la validaci??n una vez que se envian los datos del form  
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//REMPLAZA EL VBALIDADOR POR DEFECTO
		//binder.setValidator(validator);
		//A??ade al Validador
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
		usuario.setApellido("L??pez"); 
		usuario.setNombre("arca"); 
		usuario.setHabilitar(true); 
		usuario.setIdentificador("1234-123-L");
		usuario.setValorSecreto("Secretoso"); 
		usuario.setPais(new Pais(1,"ES","Espa??a"));
		usuario.setGenero("Hombre");
		usuario.setUsername("Arca");
		usuario.setCuenta(100);

		usuario.setEmail("arcadiolg2@gmail.com");
		usuario.setRoles(Arrays.asList(new Rol(1,"Administrador","ROLE_ADMIN"), new Rol(2,"Usuario","ROLE_USUARIO") ));
		model.addAttribute("titulo", "FORMULARIO"); 
		
		model.addAttribute("usuario", usuario); 
		
		return "form"; 
	}
	
	
	@ModelAttribute("paises")
	public List<String> paises(){
		return Arrays.asList("Espa??a","M??xico","Chile","Argentina","Per??");
	}
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises(){
		return paisService.listar();
	}
	
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap(){
		Map <String,String> paises = new HashMap<String,String>();
		paises.put("ES", "Espa??a"); 
		paises.put("MX", "M??xico"); 
		paises.put("CH", "Chile"); 
		paises.put("PE", "Per??"); 
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
	
	@ModelAttribute("genero")
	public List<String> listaGeneros(){
		List<String> generos = new ArrayList();
		generos.add("Hombre"); 
		generos.add("Mujer");
	
		
		return generos; 
	}
	
	@PostMapping("/form")
	/*@ModelAttribute("user")*/
	public String procesar(@Valid Usuario usuario, BindingResult result,Model model) {  /*Si el objeto tiene los mismos atributos que se reciben, se crea automaticamentw*/

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
		
		if(result.hasErrors()) {
			/*Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err ->{
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage())); 
			});   
			model.addAttribute("error",errores); */
			//System.out.println(result.getFieldErrors().get(0).getField());
			model.addAttribute("titulo", "Resultado del formulario");
			return "form"; 
		}
		
		
		
		return "redirect:/ver"; 
	}
	
	@GetMapping("/ver")
	public String ver(@SessionAttribute(name="usuario", required=false) Usuario usuario, Model model, SessionStatus status) {
		if(usuario !=null) {
			model.addAttribute("usuario", usuario); 

			status.setComplete();//elimina usuario de la sesion
			return "resultado"; 
		}else {
	
			return "redirect:/form"; 
		}
		
	}
	
}
