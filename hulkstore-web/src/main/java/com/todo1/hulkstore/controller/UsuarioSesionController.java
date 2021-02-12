package com.todo1.hulkstore.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.todo1.hulkstore.to.UsuarioTo;

import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Named
@Setter
@Getter
public class UsuarioSesionController implements Serializable {

	private static final long serialVersionUID = 1L;

	private UsuarioTo usuarioTo;

}
