package com.ceiba.laboratorio.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "prestamo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PrestamoEtity implements Serializable {

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
    @JsonIgnoreProperties(value = "usuarioClientes", allowSetters = true)
    private UsuarioEntity usuarioEntityCliente;

    @ManyToOne
    @JsonIgnoreProperties(value = "usuarioBibliotecas", allowSetters = true)
    private UsuarioEntity usuarioEntityBiblioteca;

    @ManyToOne
    @JsonIgnoreProperties(value = "prestamos", allowSetters = true)
    private LibroEntity libroEntity;
}
