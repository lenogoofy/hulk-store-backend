package com.todo1.hulkstore.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.todo1.hulkstore.modelo.Pedido;

@Stateless
public class PedidoDao extends GenericoDao<Pedido> implements Serializable {

	private static final long serialVersionUID = -8925052694506198465L;

	public PedidoDao() {
		super(Pedido.class);
	}

}
