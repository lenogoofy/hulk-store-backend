package com.todo1.hulkstore.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
@Entity
@XmlRootElement
@Table(name = "Pedido")
@Data
public class Pedido implements Serializable {

	private static final long serialVersionUID = -7243957940604315332L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pedido_id")
	private Long pedidoId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@NotNull
    @Digits(fraction = 0, integer = 4)  
	private Long cantidad;
	
	@NotNull
    @Digits(fraction = 2, integer = 4)   
	private BigDecimal subtotal;

	@Column(name = "fecha_compra")
	private LocalDateTime fechaCompra;
}
