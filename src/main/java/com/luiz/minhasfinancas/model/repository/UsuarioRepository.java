package com.luiz.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luiz.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
