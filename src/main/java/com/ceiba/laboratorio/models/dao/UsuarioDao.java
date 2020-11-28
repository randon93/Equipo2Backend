package com.ceiba.laboratorio.models.dao;

import com.ceiba.laboratorio.models.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<UsuarioEntity, Long> {
}
