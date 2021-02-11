package com.todo1.hulkstore.servicio;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.todo1.hulkstore.dao.PedidoDao;
import com.todo1.hulkstore.excepcion.NoExisteResultadosExcepcion;
import com.todo1.hulkstore.excepcion.NoGuardadoExcepcion;
import com.todo1.hulkstore.modelo.Pedido;
import com.todo1.hulkstore.modelo.Producto;
import com.todo1.hulkstore.modelo.Usuario;
import com.todo1.hulkstore.to.PedidoTo;
import com.todo1.hulkstore.util.ConstanteUtil;

@Stateless
public class TiendaBean implements Serializable {

	private static final long serialVersionUID = 719896736447328716L;
	
	@Inject
	private ProductoBean productoBean;
	
	@Inject 
	private PedidoDao pedidoDao;
	
	@Inject
	private Logger logger;
	
	public void comprarProductos(PedidoTo pedidoTo) throws NoGuardadoExcepcion {
		try {
			Producto producto = productoBean.obtenerProductoPorId(pedidoTo.getProductoId());
			if(producto.getCantidad().compareTo(pedidoTo.getCantidad()) < 0) {
				throw new NoGuardadoExcepcion(ConstanteUtil.CANTIDAD_MAYOR);
			}
			Long cantidad = producto.getCantidad() - pedidoTo.getCantidad();
			producto.setCantidad(cantidad);
			productoBean.actualizarProducto(producto);
			pedidoDao.crear(crearPedido(pedidoTo));
		} catch (NoGuardadoExcepcion | NoExisteResultadosExcepcion e) {
			logger.severe(e.getMessage());
			throw new NoGuardadoExcepcion(ConstanteUtil.NO_GUARDO);
		}
	}
	
	private Pedido crearPedido(PedidoTo pedidoTo) {
		Usuario usuario = new Usuario();
		usuario.setUsuarioId(pedidoTo.getUsuarioId());
		
		Producto producto = new Producto();
		producto.setProductoId(pedidoTo.getProductoId());
		
		Pedido nuevoPedido = new Pedido();
		nuevoPedido.setUsuario(usuario);
		nuevoPedido.setProducto(producto);
		nuevoPedido.setCantidad(pedidoTo.getCantidad());
		nuevoPedido.setSubtotal(pedidoTo.getSubtotal());
		nuevoPedido.setFechaCompra(LocalDateTime.now());
		
		return nuevoPedido;
	}
	
}
