package com.todo1.hulkstore.to;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductoTo {
	private Long productoId;
	private String nombre;
	private String descripcion;
	private Long cantidad;
	private BigDecimal precio;
	private BigDecimal subTotal;
}
