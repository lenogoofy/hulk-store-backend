package com.todo1.hulkstore.servicio;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import com.todo1.hulkstore.dao.ProductoDao;
import com.todo1.hulkstore.excepcion.NoExisteResultadosExcepcion;
import com.todo1.hulkstore.excepcion.NoGuardadoExcepcion;
import com.todo1.hulkstore.modelo.Producto;
import com.todo1.hulkstore.util.ConstanteUtil;

@Stateless
public class ProductoBean implements Serializable {

	private static final long serialVersionUID = -6838346444308254588L;
	
	@Inject 
	private ProductoDao productoDao;
	
	@Inject
	private Logger logger;
	
	public List<Producto> obtenerListaProducto() throws NoExisteResultadosExcepcion {
		List<Producto> listaProducto = productoDao.obtenerTodo();
		if(Objects.isNull(listaProducto) || listaProducto.isEmpty()) {
			throw new NoExisteResultadosExcepcion(ConstanteUtil.NO_EXISTE_DATOS);
		}
		return listaProducto;
	}
	
	public Producto obtenerProductoPorId(Long identificador) throws NoExisteResultadosExcepcion {
		Producto producto = productoDao.obtenerPorId(identificador);
		if (Objects.isNull(producto)) {
			throw new NoExisteResultadosExcepcion(ConstanteUtil.NO_EXISTE_DATOS);
		}
		return producto;
	}
	
	public void crearProducto(Producto producto) throws NoGuardadoExcepcion {
		try {
			productoDao.crear(producto);
		} catch (PersistenceException | ConstraintViolationException | NoGuardadoExcepcion e) {
			logger.severe(e.getMessage());
			throw new NoGuardadoExcepcion(ConstanteUtil.NO_GUARDO);
		}
	}

	public Producto actualizarProducto(Producto producto) {
		return productoDao.actualizar(producto);
	}
	
	public void eliminarProduct(Long identificador) {
		productoDao.eliminar(identificador);
	}
}
