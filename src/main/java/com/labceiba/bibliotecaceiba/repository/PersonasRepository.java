package com.labceiba.bibliotecaceiba.repository;

import com.labceiba.bibliotecaceiba.domain.Personas;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Personas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonasRepository extends JpaRepository<Personas, Long> {
}
