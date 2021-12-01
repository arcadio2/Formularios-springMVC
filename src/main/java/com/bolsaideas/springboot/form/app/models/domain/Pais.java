package com.bolsaideas.springboot.form.app.models.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Pais {
	
	//@NotNull
	private Integer id;
	
	@NotEmpty	
	private String Codigo;
	
	private String nombre;

	public Pais(Integer id, String codigo, String nombre) {
		this.id = id;
		Codigo = codigo;
		this.nombre = nombre;
	}

	public Pais() {

	}

	public Pais(Integer id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return Codigo;
	}

	public void setCodigo(String codigo) {
		Codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
