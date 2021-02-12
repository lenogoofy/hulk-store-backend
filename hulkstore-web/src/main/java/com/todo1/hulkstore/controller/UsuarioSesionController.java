package com.todo1.hulkstore.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.todo1.hulkstore.to.UsuarioTo;
import com.todo1.hulkstore.util.ConstantesUtil;

import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Named
@Setter
@Getter
public class UsuarioSesionController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarritoController carritoController;
	
	@Inject
	private FacesContext facesContext;

	private UsuarioTo usuarioTo;
	
	
	public boolean verificarUsuarioLogeado() {
		if(Objects.nonNull(usuarioTo)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void salir() {
		usuarioTo = null;
		carritoController.init();
		try {
			facesContext.getExternalContext().redirect("index.xhtml");
		} catch (IOException e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesUtil.ERROR, "No funciona la url.");
			facesContext.addMessage(null, m);
		}
	}

}
