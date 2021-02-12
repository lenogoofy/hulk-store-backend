package com.todo1.hulkstore.controller;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.todo1.hulkstore.excepcion.NoGuardadoExcepcion;
import com.todo1.hulkstore.modelo.Usuario;
import com.todo1.hulkstore.servicio.TiendaBean;
import com.todo1.hulkstore.servicio.UsuarioBean;
import com.todo1.hulkstore.to.PedidoTo;
import com.todo1.hulkstore.to.ProductoTo;
import com.todo1.hulkstore.util.ConstantesUtil;

@RequestScoped
@Named
public class PagarController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioSesionController usuarioSesion;

	@Inject
	private CarritoController carritoCOntroller;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private TiendaBean tiendaBean;
	


	
	@PostConstruct
	public void init() {
		
	}
	
	
	
	public String realizarCompra() {
		Map<Long, ProductoTo> mapProductoCarrito = carritoCOntroller.getMapProductoCarrito();
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
	
	public PedidoTo inicializar() {
		return new PedidoTo();
	}

}
