package com.ceiba.laboratorio.models.dao;

import com.ceiba.laboratorio.models.entity.PersonasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PersonaDao extends JpaRepository<PersonasEntity, Long> {
}