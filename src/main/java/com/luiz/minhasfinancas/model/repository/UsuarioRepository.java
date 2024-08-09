package com.luiz.minhasfinancas.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luiz.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	//find by retorna um usuario, cria uma query com where
	Optional<Usuario> findByEmail(String email);
	//exsists faz uma query com where mas so retorna true or false
	boolean existsByEmail(String email);
}
