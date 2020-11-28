package com.labceiba.bibliotecaceiba.repository;

import com.labceiba.bibliotecaceiba.domain.Usuarios;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Usuarios entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
}
