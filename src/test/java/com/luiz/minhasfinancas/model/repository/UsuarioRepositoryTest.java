package com.luiz.minhasfinancas.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.luiz.minhasfinancas.model.entity.Usuario;

//sobe todo o contexto do spring boot, com arquivos e classes não necessáriamente fundamentais pro teste
//@SpringBootTest
//Cria uma instancia do banco em memória, e ao finalizar o teste, limpa tudo, bem como
//sempre faz uma transação ao iniciar o teste, e ao fim, faz roolback dessa transação
@DataJpaTest
//faz com que não seja criado uma instancia propria do h2, e sim, use meus arquivos de config
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@Autowired
	TestEntityManager em;

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		// cenário
		Usuario usuario = criarUsuario();
		em.persist(usuario);
		// ação
		boolean result = repository.existsByEmail("luiz@email.com");

		// verificação
		Assertions.assertThat(result).isTrue();
	}

	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {

		// ação
		boolean result = repository.existsByEmail("luiz@email.com");

		// verificação
		Assertions.assertThat(result).isFalse();
	}

	@Test
	public void devePersistirUsuarioNaBaseDeDados() {
		// cenário
		Usuario user = criarUsuario();

		//ação
		repository.save(user);
		
		Assertions.assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deveBuscarUmUsuarioPorEmail() {
		//cenário
		Usuario user = criarUsuario();
		em.persist(user);
		
		//verificação
		Optional<Usuario> result = repository.findByEmail("luiz@email.com");
		
		Assertions.assertThat(result.isPresent()).isTrue();
		
	}
	
	@Test
	public void deveRetornarVazioAoBuscarUmUsuarioPorEmailQuandoNaoExistirNaBase() {
		
		//verificação
		Optional<Usuario> result = repository.findByEmail("luiz@email.com");
		
		Assertions.assertThat(result.isPresent()).isFalse();
		
	}
	
	public static Usuario criarUsuario() {
		Usuario user = Usuario.builder().nome("luiz").email("luiz@email.com").senha("senhaDoLuiz").build();
		return user;
	}
}
