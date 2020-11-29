package com.ceiba.laboratorio.models.dao;

import com.ceiba.laboratorio.models.entity.PrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoDao extends JpaRepository<PrestamoEtity, Long> {

}
