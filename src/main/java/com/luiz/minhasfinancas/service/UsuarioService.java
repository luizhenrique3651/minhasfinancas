package com.luiz.minhasfinancas.service;

import com.luiz.minhasfinancas.model.entity.Usuario;

public interface UsuarioService {

	
	Usuario autenticar(String email, String senha);
	
	Usuario cadastrar(Usuario usuario);
	
	void validarEmail(String email);
}
