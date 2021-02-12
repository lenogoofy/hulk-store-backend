package com.todo1.hulkstore.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.todo1.hulkstore.excepcion.NoGuardadoExcepcion;
import com.todo1.hulkstore.modelo.Usuario;
import com.todo1.hulkstore.servicio.UsuarioBean;
import com.todo1.hulkstore.util.ConstantesUtil;

import lombok.Getter;
import lombok.Setter;

@ViewScoped
@Named
@Getter
@Setter
public class RegistrarseController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioBean usuarioBean;

	@Inject
	private FacesContext facesContext;

	private String nombre;
	private String email;
	private String celular;
	private String contrasenia;

	public String registrar() {
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setCorreo(email);
		usuario.setCelular(celular);
		usuario.setContrasenia(contrasenia);
		
		if(usuarioBean.consultarCorreo(email)) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, ConstantesUtil.INFO, "Correo ya registrado.");
			facesContext.addMessage(null, m);
			return null;
		} else {
			try {
				usuarioBean.crearUsuario(usuario);
				return "index.xhtml";
			} catch (NoGuardadoExcepcion e) {
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesUtil.ERROR, e.getMessage());
				facesContext.addMessage(null, m);
				return null;
			}
			
		}
		
	
	}

}
