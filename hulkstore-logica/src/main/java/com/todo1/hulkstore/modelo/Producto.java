/**
 * 
 */
package com.todo1.hulkstore.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.todo1.hulkstore.util.CustomLocalDateTimeSerializer;

import lombok.Data;

/**
 * @author lenin
 *
 */
@Entity
@XmlRootElement
@Table(name = "Producto", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
@Data
public class Producto implements Serializable {

	private static final long serialVersionUID = 6112284190531498704L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "producto_id")
	private Long productoId;
	
	@NotNull
	@Size(min = 1, max = 60)
	private String nombre;
	
	@NotNull
    @Digits(fraction = 0, integer = 4)    
	private Long cantidad;
	
	@NotNull
    @Digits(fraction = 2, integer = 4)   
	private BigDecimal precio;
	
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@Column(name = "fecha_ingreso")
	private LocalDateTime fechaIngreso;
	
	private String estado;
	
}
