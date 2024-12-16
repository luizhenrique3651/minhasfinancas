package com.luiz.minhasfinancas.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luiz.minhasfinancas.exception.AutenticacaoException;
import com.luiz.minhasfinancas.exception.RegraNegocioException;
import com.luiz.minhasfinancas.model.entity.Usuario;
import com.luiz.minhasfinancas.model.repository.UsuarioRepository;
import com.luiz.minhasfinancas.service.UsuarioService;

import jakarta.transaction.Transactional;

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
		Optional<Usuario> user = repository.findByEmail(email);
		
		if(!user.isPresent()) {
			throw new AutenticacaoException("Usuário não encontrado");
		}
		
		if(!user.get().getSenha().equals(senha)) {
			throw new AutenticacaoException("Senha inválida");
		}
		
		return user.get();
	}

	@Override
	@Transactional
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
