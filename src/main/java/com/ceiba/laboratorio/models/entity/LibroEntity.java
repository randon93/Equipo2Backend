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
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "libro")
public class LibroEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "isbn", length = 100, unique = true, nullable = false)
	private String isbn;

	@Column(name = "nombre", length = 100, nullable = false)
	private String nombre;

	@Column(name = "cantidad_total", nullable = false)
	private Integer cantidadTotal;

	@Column(name = "cantidad_disponible", nullable = false)
	private Integer cantidadDisponible;

    @OneToMany(mappedBy = "libroEntity")
    private Set<PrestamoEntity> prestamoEntities;

}
