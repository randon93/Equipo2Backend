package com.labceiba.bibliotecaceiba.repository;

import com.labceiba.bibliotecaceiba.domain.Libro;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Libro entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
}
