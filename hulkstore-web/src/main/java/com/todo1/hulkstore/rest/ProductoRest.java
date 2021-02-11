package com.todo1.hulkstore.rest;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.todo1.hulkstore.excepcion.NoExisteResultadosExcepcion;
import com.todo1.hulkstore.excepcion.NoGuardadoExcepcion;
import com.todo1.hulkstore.modelo.Producto;
import com.todo1.hulkstore.servicio.ProductoBean;
import com.todo1.hulkstore.util.ConstantesUtil;

@Path("/productos")
@RequestScoped
public class ProductoRest {

	@Inject
	private ProductoBean productoBean;

	private Response.ResponseBuilder responseBuilder;
	private Map<String, String> responseObj = new HashMap<>();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerListaProducto() {
		try {
			responseBuilder = Response.ok(productoBean.obtenerListaProducto());
		} catch (NoExisteResultadosExcepcion e) {
			responseObj.put(ConstantesUtil.INFO, e.getMessage());
			responseBuilder = Response.status(Response.Status.NOT_FOUND).entity(responseObj);
		}
		return responseBuilder.build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerProducto(@PathParam("id") Long id) {
		try {
			responseBuilder = Response.ok(productoBean.obtenerProductoPorId(id));
		} catch (NoExisteResultadosExcepcion e) {
			responseObj.put(ConstantesUtil.INFO, e.getMessage());
			responseBuilder = Response.status(Response.Status.NOT_FOUND).entity(responseObj);
		}
		return responseBuilder.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Producto producto) {
		try {
			productoBean.crearProducto(producto);
			responseBuilder = Response.ok(producto);
		} catch(NoGuardadoExcepcion e) {
			responseObj.put(ConstantesUtil.ERROR, e.getMessage());
			responseBuilder = Response.status(Response.Status.EXPECTATION_FAILED).entity(responseObj);
		}
		return responseBuilder.build();
	}

}
