package com.todo1.hulkstore.servicio;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import com.todo1.hulkstore.dao.UsuarioDao;
import com.todo1.hulkstore.excepcion.ExisteResultadosExcepcion;
import com.todo1.hulkstore.excepcion.NoExisteResultadosExcepcion;
import com.todo1.hulkstore.excepcion.NoGuardadoExcepcion;
import com.todo1.hulkstore.modelo.Usuario;
import com.todo1.hulkstore.to.UsuarioTo;
import com.todo1.hulkstore.util.ConstanteUtil;

@Stateless
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = -8543411920751729314L;

	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private Logger logger;

	public List<Usuario> obtenerListaUsuario() throws NoExisteResultadosExcepcion {
		List<Usuario> listaUsuario = usuarioDao.obtenerTodo();
		if(Objects.isNull(listaUsuario) || listaUsuario.isEmpty()) {
			throw new NoExisteResultadosExcepcion(ConstanteUtil.NO_EXISTE_DATOS);
		}
		return listaUsuario;
	}

	public Usuario obtenerUsuarioPorId(Long identificador) throws NoExisteResultadosExcepcion {
		Usuario usuario = usuarioDao.obtenerPorId(identificador);
		if (Objects.isNull(usuario)) {
			throw new NoExisteResultadosExcepcion(ConstanteUtil.NO_EXISTE_DATOS);
		}
		return usuario;
	}

	public void crearUsuario(Usuario usuario) throws NoGuardadoExcepcion {
		try {
			usuarioDao.crear(usuario);
		} catch (PersistenceException | ConstraintViolationException | NoGuardadoExcepcion e) {
			logger.severe(e.getMessage());
			throw new NoGuardadoExcepcion(ConstanteUtil.NO_GUARDO);
		}
	}

	public Usuario actualizarUsuario(Usuario usuario) {
		return usuarioDao.actualizar(usuario);
	}
	
	public UsuarioTo consultarUsuarioInicioSesion(String email, String contrasenia) throws NoExisteResultadosExcepcion {
		UsuarioTo usuarioTo = usuarioDao.consultarUsuarioInicioSesion(email, contrasenia);
		if(Objects.isNull(usuarioTo)) {
			throw new NoExisteResultadosExcepcion(ConstanteUtil.NO_EXISTE_DATOS);
		}
		return usuarioTo;
	}
	
	public boolean consultarCorreo(String email) {
		return usuarioDao.consultarCorreo(email);
	}
}
