package com.bolsaideas.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsaideas.springboot.form.app.models.domain.Pais;

@Service
public class PaisServiceImpl implements PaisService {

	private List<Pais> paises; 
	
	public PaisServiceImpl() {
		Pais españa = new Pais(1,"ES","España"); 
		Pais mexico = new Pais(2,"MX","México"); 
		Pais chile = new Pais(3,"CH","Chile"); 
		Pais argentina = new Pais(4,"ARG","Argentina"); 
		Pais peru = new Pais(5,"PE","Perú"); 
		Pais colombia = new Pais(6,"CO","Colombia"); 
		Pais venezuela = new Pais(7,"VE","Venezuela"); 
		 
		this.paises = Arrays.asList(españa,mexico,chile,argentina,peru,colombia,venezuela);
	}

	@Override
	public List<Pais> listar() {
		
		return paises;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		Pais resultado = null;
		for(Pais pais: paises) {
			if(id == pais.getId()) {
				resultado=pais;
			}
		}
		return resultado;
	}

}
