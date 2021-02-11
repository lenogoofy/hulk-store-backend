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
import com.todo1.hulkstore.modelo.Usuario;
import com.todo1.hulkstore.servicio.UsuarioBean;
import com.todo1.hulkstore.util.ConstantesUtil;

@Path("/usuarios")
@RequestScoped
public class RegistrarRest {

	@Inject
	private UsuarioBean usuarioBean;

	private	Response.ResponseBuilder responseBuilder;
	private Map<String, String> responseObj = new HashMap<>();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerListaUsuario() {
		try {
			responseBuilder = Response.ok(usuarioBean.obtenerListaUsuario());
		} catch (NoExisteResultadosExcepcion e) {
			responseObj.put(ConstantesUtil.INFO, e.getMessage());
			responseBuilder = Response.status(Response.Status.NOT_FOUND).entity(responseObj);
		}
		return responseBuilder.build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerUsuario(@PathParam("id") Long id) {
		try {
			responseBuilder = Response.ok(usuarioBean.obtenerUsuarioPorId(id));
		} catch (NoExisteResultadosExcepcion e) {
			responseObj.put(ConstantesUtil.INFO, e.getMessage());
			responseBuilder = Response.status(Response.Status.NOT_FOUND).entity(responseObj);
		}
		return responseBuilder.build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crear(Usuario usuario) {
		try {
			usuarioBean.crearUsuario(usuario);
			responseBuilder = Response.ok(usuario);
		} catch (NoGuardadoExcepcion e) {
			responseObj.put(ConstantesUtil.ERROR, e.getMessage());
			responseBuilder = Response.status(Response.Status.EXPECTATION_FAILED).entity(responseObj);
		} 
		return responseBuilder.build();
	}
}
