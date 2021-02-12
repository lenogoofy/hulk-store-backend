package com.todo1.hulkstore.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.todo1.hulkstore.excepcion.NoExisteResultadosExcepcion;
import com.todo1.hulkstore.excepcion.NoGuardadoExcepcion;
import com.todo1.hulkstore.modelo.Producto;
import com.todo1.hulkstore.servicio.ProductoBean;

import lombok.Getter;
import lombok.Setter;


@ViewScoped
@Setter
@Getter
@Named
public class ProductoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProductoBean productoBean;
	
	@Inject
    private FacesContext facesContext;
	
	@Inject
	private CarritoController carrito;
	
	private static Logger logger = Logger.getLogger(ProductoController.class);
	
	private List<Producto> listaProducto;
	
	private Producto nuevoProducto;
	
	private String nombre;
	private Long cantidad;
	private BigDecimal precio;
	
	private Producto productoSeleccionado;
	
	@PostConstruct
    public void init() {
        try {
			listaProducto = productoBean.obtenerListaProducto();
			nuevoProducto = new Producto();
		} catch (NoExisteResultadosExcepcion e) {
			listaProducto = null;
			logger.info(e.getMessage());
		}
    }
	
	public String registrar() {
        try {
        	nuevoProducto.setNombre(nombre);
        	nuevoProducto.setCantidad(cantidad);
        	nuevoProducto.setPrecio(precio);
        	productoBean.crearProducto(nuevoProducto);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado!", "Registro con éxito");
            facesContext.addMessage(null, m);
            init();
            return "producto-lista.xhtml";
        } catch (NoGuardadoExcepcion e) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Registration unsuccessful");
            facesContext.addMessage(null, m);
            return null;
        }
    }
	
	public void obtenerProducto() {
		carrito.gestionarcarrito(productoSeleccionado);
		logger.info(productoSeleccionado.getNombre());
	}
	
	
}
