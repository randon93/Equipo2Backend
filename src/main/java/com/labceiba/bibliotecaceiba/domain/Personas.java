package com.labceiba.bibliotecaceiba.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Personas.
 */
@Entity
@Table(name = "personas")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Personas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 10)
    @Column(name = "tipo_identificacion", length = 10)
    private String tipoIdentificacion;

    @Size(max = 30)
    @Column(name = "identificacion", length = 30)
    private String identificacion;

    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    private String nombre;

    @Size(max = 100)
    @Column(name = "apellido", length = 100)
    private String apellido;

    @Size(max = 100)
    @Column(name = "direccion", length = 100)
    private String direccion;

    @Size(max = 20)
    @Column(name = "telefono", length = 20)
    private String telefono;

    @OneToMany(mappedBy = "personas")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Usuarios> usuarios = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public Personas tipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
        return this;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public Personas identificacion(String identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Personas nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Personas apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public Personas direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Personas telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Set<Usuarios> getUsuarios() {
        return usuarios;
    }

    public Personas usuarios(Set<Usuarios> usuarios) {
        this.usuarios = usuarios;
        return this;
    }

    public Personas addUsuarios(Usuarios usuarios) {
        this.usuarios.add(usuarios);
        usuarios.setPersonas(this);
        return this;
    }

    public Personas removeUsuarios(Usuarios usuarios) {
        this.usuarios.remove(usuarios);
        usuarios.setPersonas(null);
        return this;
    }

    public void setUsuarios(Set<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Personas)) {
            return false;
        }
        return id != null && id.equals(((Personas) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Personas{" +
            "id=" + getId() +
            ", tipoIdentificacion='" + getTipoIdentificacion() + "'" +
            ", identificacion='" + getIdentificacion() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", telefono='" + getTelefono() + "'" +
            "}";
    }
}
