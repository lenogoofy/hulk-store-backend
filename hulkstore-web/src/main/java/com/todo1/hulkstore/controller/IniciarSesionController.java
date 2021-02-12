package com.todo1.hulkstore.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.todo1.hulkstore.excepcion.NoExisteResultadosExcepcion;
import com.todo1.hulkstore.servicio.UsuarioBean;
import com.todo1.hulkstore.to.UsuarioTo;

import lombok.Getter;
import lombok.Setter;

@RequestScoped
@Named
@Getter
@Setter
public class IniciarSesionController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioBean usuarioBean;
	
	@Inject
	private UsuarioSesionController usuarioSesion;
	
	@Inject
	private FacesContext facesContext;
	
	private String email;
	private String contrasenia;
	
	public String iniciarSesion() {
		try {
			UsuarioTo usuarioTo = usuarioBean.consultarUsuarioInicioSesion(email, contrasenia);
			usuarioSesion.setUsuarioTo(usuarioTo);
			return "index.xhtml";
		} catch (NoExisteResultadosExcepcion e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informeción!", "Las credenciales no son las correctas");
            facesContext.addMessage(null, m);
            return null;
		}
	}
	

}
