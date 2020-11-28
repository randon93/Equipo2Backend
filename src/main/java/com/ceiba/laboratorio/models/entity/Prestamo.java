package com.com.ceiba.laboratorio.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Prestamo.
 */
@Entity
@Table(name = "prestamo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Prestamo implements Serializable {

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
    private Usuarios usuarioCliente;

    @ManyToOne
    @JsonIgnoreProperties(value = "usuarioBibliotecas", allowSetters = true)
    private Usuarios usuarioBiblioteca;

    @ManyToOne
    @JsonIgnoreProperties(value = "prestamos", allowSetters = true)
    private Libro libro;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Prestamo fechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
        return this;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public Prestamo fechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
        return this;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public LocalDate getFechaEntregado() {
        return fechaEntregado;
    }

    public Prestamo fechaEntregado(LocalDate fechaEntregado) {
        this.fechaEntregado = fechaEntregado;
        return this;
    }

    public void setFechaEntregado(LocalDate fechaEntregado) {
        this.fechaEntregado = fechaEntregado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Prestamo observaciones(String observaciones) {
        this.observaciones = observaciones;
        return this;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Usuarios getUsuarioCliente() {
        return usuarioCliente;
    }

    public Prestamo usuarioCliente(Usuarios usuarios) {
        this.usuarioCliente = usuarios;
        return this;
    }

    public void setUsuarioCliente(Usuarios usuarios) {
        this.usuarioCliente = usuarios;
    }

    public Usuarios getUsuarioBiblioteca() {
        return usuarioBiblioteca;
    }

    public Prestamo usuarioBiblioteca(Usuarios usuarios) {
        this.usuarioBiblioteca = usuarios;
        return this;
    }

    public void setUsuarioBiblioteca(Usuarios usuarios) {
        this.usuarioBiblioteca = usuarios;
    }

    public Libro getLibro() {
        return libro;
    }

    public Prestamo libro(Libro libro) {
        this.libro = libro;
        return this;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prestamo)) {
            return false;
        }
        return id != null && id.equals(((Prestamo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Prestamo{" +
            "id=" + getId() +
            ", fechaPrestamo='" + getFechaPrestamo() + "'" +
            ", fechaEntrega='" + getFechaEntrega() + "'" +
            ", fechaEntregado='" + getFechaEntregado() + "'" +
            ", observaciones='" + getObservaciones() + "'" +
            "}";
    }
}
