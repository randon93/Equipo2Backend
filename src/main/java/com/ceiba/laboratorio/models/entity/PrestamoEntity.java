package com.ceiba.laboratorio.models.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "prestamo")
public class PrestamoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_prestamo")
	private LocalDate fechaPrestamo;

	@Column(name = "fecha_entrega")
	private LocalDate fechaEntrega;

	@Column(name = "fecha_entregado")
	private LocalDate fechaEntregado;

	@Lob
	@Column(name = "observaciones")
	private String observaciones;

	@ManyToOne
	@JoinColumn(name = "id_usuario_cliente")
	@JsonIgnoreProperties(value = "usuarioClientes", allowSetters = true)
	private UsuarioEntity usuarioEntityCliente;

	@ManyToOne
	@JoinColumn(name = "id_usuario_bibliotecario")
	@JsonIgnoreProperties(value = "usuarioBibliotecas", allowSetters = true)
	private UsuarioEntity usuarioEntityBiblioteca;

	@ManyToOne
	@JoinColumn(name = "id_libro")
	@JsonIgnoreProperties(value = "prestamos", allowSetters = true)
	private LibroEntity libroEntity;
}
