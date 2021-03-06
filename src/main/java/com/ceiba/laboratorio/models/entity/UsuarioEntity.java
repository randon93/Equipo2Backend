package com.ceiba.laboratorio.models.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

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
	private PersonasEntity personasEntity;

//	@OneToMany(mappedBy = "usuarioEntityCliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private Set<PrestamoEntity> usuarioClientes;
//
//	@OneToMany(mappedBy = "usuarioEntityBiblioteca", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private Set<PrestamoEntity> usuarioBibliotecas;

}
