package com.bolsaideas.springboot.form.app.models.domain;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.bolsaideas.springboot.form.app.validation.IndetificadorRegex;
import com.bolsaideas.springboot.form.app.validation.Requerido;

public class Usuario {
	
	//@Pattern(regexp = "[0-9]{4}[-][\\d]{3}[-][A-Z]{1}")
	@IndetificadorRegex
	private String identificador; 

	//@NotEmpty(message="EL nombre no puede ser vacío")
	private String nombre; 
	//@NotBlank
	@Requerido
	private String apellido; 
	@NotBlank
	@Size(min = 3, max = 8)
	private String username;
	@NotEmpty 
	private String password;
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	//@Past
	@Future
	//@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechanacimiento; 

	
	@NotNull // ES para objetos
	@Min(5)
	@Max(5000)
	private Integer cuenta; 
	
	@NotNull
	private Pais pais; 
	
	private boolean habilitar; 
	
	@NotEmpty
	private List<Rol> roles; 
	
	@NotEmpty
	private String genero;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public Integer getCuenta() {
		return cuenta;
	}

	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public boolean isHabilitar() {
		return habilitar;
	}

	public void setHabilitar(boolean habilitar) {
		this.habilitar = habilitar;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
	
}
