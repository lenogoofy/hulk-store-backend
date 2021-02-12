package com.todo1.hulkstore.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import com.todo1.hulkstore.modelo.Usuario;
import com.todo1.hulkstore.to.UsuarioTo;

@Stateless
public class UsuarioDao extends GenericoDao<Usuario, BigInteger> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String cadenaConsulta;
	private TypedQuery<Usuario> tipoConsulta;

	public UsuarioDao() {
		super(Usuario.class);
	}
	
	public UsuarioTo consultarUsuarioInicioSesion(String email, String contrasenia) {
		cadenaConsulta = "select u from Usuario u where u.correo = :email and u.contrasenia = :contrasenia";
		tipoConsulta = em.createQuery(cadenaConsulta, Usuario.class);
		tipoConsulta.setParameter("email", email);
		tipoConsulta.setParameter("contrasenia", contrasenia);
		List<Usuario> listaUsuario = tipoConsulta.getResultList();
		if(Objects.nonNull(listaUsuario) && !listaUsuario.isEmpty()) {
			UsuarioTo usuarioTo = new UsuarioTo();
			usuarioTo.setCodigo(listaUsuario.get(0).getUsuarioId());
			usuarioTo.setNombre(listaUsuario.get(0).getNombre());
			usuarioTo.setEmail(listaUsuario.get(0).getCorreo());
			return usuarioTo;
		} else {
			return null;
		}
	}
	
	
	public boolean consultarCorreo(String email) {
		cadenaConsulta = "select u from Usuario u where u.correo = :email";
		tipoConsulta = em.createQuery(cadenaConsulta, Usuario.class);
		tipoConsulta.setParameter("email", email);
		List<Usuario> listaUsuario = tipoConsulta.getResultList();
		if(Objects.nonNull(listaUsuario) && !listaUsuario.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
