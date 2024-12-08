package com.luiz.minhasfinancas.service;


import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.luiz.minhasfinancas.exception.RegraNegocioException;
import com.luiz.minhasfinancas.model.entity.Usuario;
import com.luiz.minhasfinancas.model.repository.UsuarioRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@Autowired
	UsuarioService service;
	
	@Autowired
	UsuarioRepository repository;
	
	@Test
	public void deveValidarEmail() {
		
		//cenário
		repository.deleteAll();
		
		//ação
		service.validarEmail("email@email.com");
	}
	
	@Test
	public void deveLancarErroAoValidarQuandoExistirEmailCadastrado() {
		//cenário
		Usuario user = Usuario.builder().nome("Luiz").email("luiz@email.com").build();
		repository.save(user);		
		//metodo que recebe uma lambda que traz a ação que lançará a exceção especificada
		 assertThrows(RegraNegocioException.class, () -> {
			//ação
				service.validarEmail("luiz@email.com");
		    });
		
	}
}
