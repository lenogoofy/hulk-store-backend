package com.todo1.hulkstore.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.todo1.hulkstore.modelo.Usuario;

@Stateless
public class UsuarioDao extends GenericoDao<Usuario> implements Serializable {

	private static final long serialVersionUID = 1L;

	public UsuarioDao() {
		super(Usuario.class);
	}

}
