package com.luiz.minhasfinancas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luiz.minhasfinancas.exception.RegraNegocioException;
import com.luiz.minhasfinancas.model.entity.Usuario;
import com.luiz.minhasfinancas.model.repository.UsuarioRepository;
import com.luiz.minhasfinancas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	//instância de repositório para realizar operações com o banco
	@Autowired
	private UsuarioRepository repository;
	
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario cadastrar(Usuario usuario) {

		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário com esse email");
		}
	}

}
