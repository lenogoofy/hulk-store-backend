package com.todo1.hulkstore.rest;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.todo1.hulkstore.excepcion.NoGuardadoExcepcion;
import com.todo1.hulkstore.servicio.TiendaBean;
import com.todo1.hulkstore.to.PedidoTo;
import com.todo1.hulkstore.util.ConstantesUtil;

@Path("/tienda")
@RequestScoped
public class TiendaRest {

	private static final long serialVersionUID = -2334366294949357626L;
	
	@Inject
	private TiendaBean tiendaBean;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response comprar(PedidoTo pedidoTo) {
		Response.ResponseBuilder responseBuilder = null;
		try {
			tiendaBean.comprarProductos(pedidoTo);
			responseBuilder = Response.ok(ConstantesUtil.EXITO);
		} catch (NoGuardadoExcepcion e) {
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put(ConstantesUtil.ERROR, e.getMessage());
			responseBuilder = Response.status(Response.Status.EXPECTATION_FAILED).entity(responseObj);
		} 
		return responseBuilder.build();
	}
	
}
