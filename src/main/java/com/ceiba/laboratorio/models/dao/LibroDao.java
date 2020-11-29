package com.ceiba.laboratorio.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.laboratorio.models.entity.LibroEntity;

@Repository
public interface LibroDao extends JpaRepository<LibroEntity, Long> {

	LibroEntity findByIsbn(String isbn);
}