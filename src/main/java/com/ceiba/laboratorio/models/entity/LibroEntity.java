package com.ceiba.laboratorio.models.entity;

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
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "libro")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LibroEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn", length = 100)
    private String isbn;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "cantidad_total")
    private Integer cantidadTotal;

    @Column(name = "cantidad_disponible")
    private Integer cantidadDisponible;

    @OneToMany(mappedBy = "libro")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PrestamoEtity> prestamoEtities = new HashSet<>();
}
