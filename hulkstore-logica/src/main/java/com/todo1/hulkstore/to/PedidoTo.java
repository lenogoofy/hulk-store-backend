package com.todo1.hulkstore.to;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PedidoTo {
	private Long usuarioId;
	private Long productoId;
	private Long cantidad;
	private BigDecimal subtotal;
}
