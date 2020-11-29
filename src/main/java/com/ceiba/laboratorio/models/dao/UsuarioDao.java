package com.ceiba.laboratorio.models.dao;

import com.ceiba.laboratorio.models.domain.RespuestaDomain;
import com.ceiba.laboratorio.models.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
/**
 * Repositorio para usuarios con metodo personaliado
 * @author andres
 *
 */
@Repository
public interface UsuarioDao extends JpaRepository<UsuarioEntity, Long> {
	/**
	 * Busca el usuario por el correo que se designe y retorna sus datos si lo encuentra
	 * @param correo
	 * @return
	 */
	@Query("SELECT user FROM UsuarioEntity user WHERE LOWER(user.correo) = LOWER(:correo)")
	public S findByCorreo(@Param("correo") String correo);
	
	public S save(Optional<S> usuario);
	
	public S findAll();
	
	public S findById(Long id);
	
	//public S update(UsuarioEntity usuarioNuevo);
	
    //public S delete(Long id);
}
