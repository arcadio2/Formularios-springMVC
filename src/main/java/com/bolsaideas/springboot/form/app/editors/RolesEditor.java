package com.bolsaideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsaideas.springboot.form.app.services.RolService;


@Component
public class RolesEditor  extends PropertyEditorSupport{

	@Autowired
	private RolService service;
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		try {
			Integer id = Integer.parseInt(text);
			setValue(service.obtenerPorId(id)); 
		}catch(Exception e) {
			setValue(null); 
		}
	
	}

}
