package com.todo1.hulkstore.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import com.todo1.hulkstore.modelo.Producto;

@Stateless
public class ProductoDao extends GenericoDao<Producto> implements Serializable {

	private static final long serialVersionUID = 7375177647725230918L;

	public ProductoDao() {
		super(Producto.class);
	}

}
