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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "usuarios")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "correo", length = 100)
    private String correo;

    @Column(name = "clave", length = 100)
    private String clave;

    @Column(name = "rol", length = 1)
    private String rol;

    @OneToMany(mappedBy = "usuarioCliente")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PrestamoEtity> usuarioClientes = new HashSet<>();

    @OneToMany(mappedBy = "usuarioBiblioteca")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<PrestamoEtity> usuarioBibliotecas = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "usuarios", allowSetters = true)
    private PersonasEntity personasEntity;
}
