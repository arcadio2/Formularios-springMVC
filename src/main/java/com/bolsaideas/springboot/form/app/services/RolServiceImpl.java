package com.bolsaideas.springboot.form.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsaideas.springboot.form.app.models.domain.Rol;

@Service
public class RolServiceImpl implements RolService{
	private List<Rol> roles;
	
	
	public RolServiceImpl() {
		roles = new ArrayList<>();
		this.roles.add(new Rol(1,"Administrador","ROLE_ADMIN"));
		this.roles.add(new Rol(2,"Usuario","ROLE_USUARIO"));
		this.roles.add(new Rol(3,"Moderador","ROLE_MODERADOR"));
	}

	@Override
	public List<Rol> listar() {
		
		return roles;
	}

	@Override
	public Rol obtenerPorId(Integer id) {
		Rol seleccionado = null; 
		for(Rol r:roles) {
			if(r.getId() == id) {
				seleccionado = r; 
				break; 
			}
		}
		
		return seleccionado;
	}
	
	

}
