package com.ceiba.laboratorio.models.dao;

import com.ceiba.laboratorio.models.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByCorreo(String correo);

}
