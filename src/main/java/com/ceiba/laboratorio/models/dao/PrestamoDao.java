package com.ceiba.laboratorio.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.laboratorio.models.entity.PrestamoEntity;

@Repository
public interface PrestamoDao extends JpaRepository<PrestamoEntity, Long> {

}