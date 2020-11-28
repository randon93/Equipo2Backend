package com.com.ceiba.laboratorio.models.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Libro.
 */
@Entity
@Table(name = "libro")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @Column(name = "isbn", length = 100)
    private String isbn;

    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "cantidad_total")
    private Integer cantidadTotal;

    @Column(name = "cantidad_disponible")
    private Integer cantidadDisponible;

    @OneToMany(mappedBy = "libro")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Prestamo> prestamos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public Libro isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNombre() {
        return nombre;
    }

    public Libro nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public Libro cantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
        return this;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Integer getCantidadDisponible() {
        return cantidadDisponible;
    }

    public Libro cantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
        return this;
    }

    public void setCantidadDisponible(Integer cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public Set<Prestamo> getPrestamos() {
        return prestamos;
    }

    public Libro prestamos(Set<Prestamo> prestamos) {
        this.prestamos = prestamos;
        return this;
    }

    public Libro addPrestamo(Prestamo prestamo) {
        this.prestamos.add(prestamo);
        prestamo.setLibro(this);
        return this;
    }

    public Libro removePrestamo(Prestamo prestamo) {
        this.prestamos.remove(prestamo);
        prestamo.setLibro(null);
        return this;
    }

    public void setPrestamos(Set<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Libro)) {
            return false;
        }
        return id != null && id.equals(((Libro) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Libro{" +
            "id=" + getId() +
            ", isbn='" + getIsbn() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", cantidadTotal=" + getCantidadTotal() +
            ", cantidadDisponible=" + getCantidadDisponible() +
            "}";
    }
}
