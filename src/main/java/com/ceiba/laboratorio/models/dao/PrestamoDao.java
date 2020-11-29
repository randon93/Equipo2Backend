package com.ceiba.laboratorio.models.dao;

import com.ceiba.laboratorio.models.entity.PrestamoEtity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoDao extends JpaRepository<PrestamoEtity, Long> {
}
