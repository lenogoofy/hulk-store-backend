package com.todo1.hulkstore.dao;

import java.math.BigInteger;

import javax.ejb.Stateless;

import com.todo1.hulkstore.modelo.Producto;

@Stateless
public class ProductoDao extends GenericoDao<Producto, BigInteger> {

	public ProductoDao() {
		super(Producto.class);
	}

}
