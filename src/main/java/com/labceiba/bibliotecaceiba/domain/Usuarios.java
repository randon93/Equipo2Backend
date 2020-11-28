package com.labceiba.bibliotecaceiba.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Usuarios.
 */
@Entity
@Table(name = "usuarios")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @Column(name = "correo", length = 100)
    private String correo;

    @Size(max = 100)
    @Column(name = "clave", length = 100)
    private String clave;

    @Size(max = 1)
    @Column(name = "rol", length = 1)
    private String rol;

    @OneToMany(mappedBy = "usuarioCliente")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Prestamo> usuarioClientes = new HashSet<>();

    @OneToMany(mappedBy = "usuarioBiblioteca")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Prestamo> usuarioBibliotecas = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "usuarios", allowSetters = true)
    private Personas personas;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public Usuarios correo(String correo) {
        this.correo = correo;
        return this;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public Usuarios clave(String clave) {
        this.clave = clave;
        return this;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public Usuarios rol(String rol) {
        this.rol = rol;
        return this;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Set<Prestamo> getUsuarioClientes() {
        return usuarioClientes;
    }

    public Usuarios usuarioClientes(Set<Prestamo> prestamos) {
        this.usuarioClientes = prestamos;
        return this;
    }

    public Usuarios addUsuarioCliente(Prestamo prestamo) {
        this.usuarioClientes.add(prestamo);
        prestamo.setUsuarioCliente(this);
        return this;
    }

    public Usuarios removeUsuarioCliente(Prestamo prestamo) {
        this.usuarioClientes.remove(prestamo);
        prestamo.setUsuarioCliente(null);
        return this;
    }

    public void setUsuarioClientes(Set<Prestamo> prestamos) {
        this.usuarioClientes = prestamos;
    }

    public Set<Prestamo> getUsuarioBibliotecas() {
        return usuarioBibliotecas;
    }

    public Usuarios usuarioBibliotecas(Set<Prestamo> prestamos) {
        this.usuarioBibliotecas = prestamos;
        return this;
    }

    public Usuarios addUsuarioBiblioteca(Prestamo prestamo) {
        this.usuarioBibliotecas.add(prestamo);
        prestamo.setUsuarioBiblioteca(this);
        return this;
    }

    public Usuarios removeUsuarioBiblioteca(Prestamo prestamo) {
        this.usuarioBibliotecas.remove(prestamo);
        prestamo.setUsuarioBiblioteca(null);
        return this;
    }

    public void setUsuarioBibliotecas(Set<Prestamo> prestamos) {
        this.usuarioBibliotecas = prestamos;
    }

    public Personas getPersonas() {
        return personas;
    }

    public Usuarios personas(Personas personas) {
        this.personas = personas;
        return this;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Usuarios)) {
            return false;
        }
        return id != null && id.equals(((Usuarios) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Usuarios{" +
            "id=" + getId() +
            ", correo='" + getCorreo() + "'" +
            ", clave='" + getClave() + "'" +
            ", rol='" + getRol() + "'" +
            "}";
    }
}
