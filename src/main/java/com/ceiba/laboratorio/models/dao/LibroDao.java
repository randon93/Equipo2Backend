package com.ceiba.laboratorio.models.dao;

import com.ceiba.laboratorio.models.entity.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroDao extends JpaRepository<LibroEntity, Long> {

    LibroEntity findByIsbn(String isbn);
}
