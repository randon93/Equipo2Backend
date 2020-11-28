package com.labceiba.bibliotecaceiba.repository;

import com.labceiba.bibliotecaceiba.domain.Prestamo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Prestamo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}
