package com.todo1.hulkstore.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.todo1.hulkstore.excepcion.NoGuardadoExcepcion;
import com.todo1.hulkstore.modelo.Producto;
import com.todo1.hulkstore.servicio.TiendaBean;
import com.todo1.hulkstore.to.PedidoTo;
import com.todo1.hulkstore.to.ProductoTo;
import com.todo1.hulkstore.util.ConstantesUtil;

import lombok.Getter;
import lombok.Setter;

@SessionScoped
@Named
@Setter
@Getter
public class CarritoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private TiendaBean tiendaBean;

	private Map<Long, ProductoTo> mapProductoCarrito;

	private BigDecimal montoTotal;

	@PostConstruct
	public void init() {
		montoTotal = BigDecimal.ZERO;
		mapProductoCarrito = new ConcurrentHashMap<>();
	}

	public void gestionarcarrito(Producto producto) {
		Long codigoProducto = producto.getProductoId();
		ProductoTo productoTo = new ProductoTo();
		productoTo.setProductoId(codigoProducto);
		productoTo.setNombre(producto.getNombre());
		productoTo.setDescripcion(producto.getDescripcion());
		productoTo.setPrecio(producto.getPrecio());
		if (mapProductoCarrito.get(codigoProducto) == null) {
			productoTo.setCantidad(1L);
			productoTo.setSubTotal(producto.getPrecio());
			mapProductoCarrito.put(codigoProducto, productoTo);
		} else {
			Long cantidad = mapProductoCarrito.get(codigoProducto).getCantidad() + 1L;
			BigDecimal cantidadTotal = new BigDecimal(cantidad);
			BigDecimal precio = productoTo.getPrecio().multiply(cantidadTotal);
			mapProductoCarrito.get(codigoProducto).setCantidad(cantidad);
			mapProductoCarrito.get(codigoProducto).setSubTotal(precio);
		}

		montoTotal = montoTotal.add(producto.getPrecio());
	}

	public String realizarCompra() {
		String mensajeError = null;
		if (Objects.nonNull(mapProductoCarrito) && !mapProductoCarrito.isEmpty()) {
			for (ProductoTo productoTo : mapProductoCarrito.values()) {
				PedidoTo pedidoTo = inicializar();
				pedidoTo.setUsuarioId(2L);
				pedidoTo.setProductoId(productoTo.getProductoId());
				pedidoTo.setCantidad(productoTo.getCantidad());
				pedidoTo.setSubtotal(productoTo.getSubTotal());
				try {
					tiendaBean.comprarProductos(pedidoTo);
				} catch (NoGuardadoExcepcion e) {
					mensajeError = e.getMessage();
					break;
				}
			}
			if (Objects.nonNull(mensajeError)) {
				FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, ConstantesUtil.ERROR, mensajeError);
				facesContext.addMessage(null, m);
			}
			init();
			return "index.xhtml";
		} else {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, ConstantesUtil.INFO,
					"No existe productos para comprar.");
			facesContext.addMessage(null, m);
			return null;
		}
	}

	public void eliminarProducto(ProductoTo productoTo) {
		Long codigoProducto = productoTo.getProductoId();
		if (Objects.nonNull(mapProductoCarrito.get(codigoProducto))) {
			Long cantidad = mapProductoCarrito.get(codigoProducto).getCantidad() - 1L;
			BigDecimal cantidadTotal = new BigDecimal(cantidad);
			BigDecimal precio = productoTo.getPrecio().multiply(cantidadTotal);
			mapProductoCarrito.get(codigoProducto).setCantidad(cantidad);
			mapProductoCarrito.get(codigoProducto).setSubTotal(precio);
			montoTotal = montoTotal.subtract(productoTo.getPrecio());
			if (cantidad == 0) {
				mapProductoCarrito.remove(codigoProducto);
			}
		}
	}

	public String irATienda() {
		return "index.xhtml";
	}

	public PedidoTo inicializar() {
		return new PedidoTo();
	}
	public String verCarrito() {
		return "carrito.xhtml";
	}
}
