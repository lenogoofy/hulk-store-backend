package com.todo1.hulkstore.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
@Entity
@XmlRootElement
@Table(name = "Usuario", uniqueConstraints = @UniqueConstraint(columnNames = "correo"))
@Data
public class Usuario implements Serializable {

	private static final long serialVersionUID = -1847054823702538943L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id")
	private Long usuarioId;

	@NotNull
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[^0-9]*", message = "No debe contener números")
	private String nombre;

	@NotNull
	@NotEmpty
	@Email
	private String correo;

	private String celular;
	
	private String contrasenia;
	
}
