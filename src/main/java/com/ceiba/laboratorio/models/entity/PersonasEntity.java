package com.ceiba.laboratorio.models.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "persona")
public class PersonasEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tipo_identificacion", length = 10)
	private String tipoIdentificacion;

	@Column(name = "identificacion", length = 30)
	private String identificacion;

	@Column(name = "nombre", length = 100)
	private String nombre;

	@Column(name = "apellido", length = 100)
	private String apellido;

	@Column(name = "direccion", length = 100)
	private String direccion;

	@Column(name = "telefono", length = 20)
	private String telefono;

	@OneToMany(mappedBy = "personas")
	private Set<UsuarioEntity> usuarioEntities = new HashSet<>();

}
