package com.ceiba.laboratorio.models.entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "correo", length = 100, unique = true)
	private String correo;

	@Column(name = "clave", length = 100)
	private String clave;

	@Column(name = "rol", length = 1)
	private String rol;

	@ManyToOne
	@JoinColumn(name = "id_persona")
	@JsonIgnoreProperties(value = "usuarios", allowSetters = true)
	private PersonasEntity personasEntity;

//	@OneToMany(mappedBy = "usuarioCliente")
//	private Set<PrestamoEntity> usuarioClientes = new HashSet<>();
//
//	@OneToMany(mappedBy = "usuarioBiblioteca")
//	private Set<PrestamoEntity> usuarioBibliotecas = new HashSet<>();

}
