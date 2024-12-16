package com.luiz.minhasfinancas.model.repository;

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
		//cenário
		Usuario usuario = Usuario.builder().nome("luiz").email("luiz@gmail.com").build();
		em.persist(usuario);
		//ação
		boolean result =repository.existsByEmail("luiz@gmail.com");
		
		//verificação
		Assertions.assertThat(result).isTrue();
	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {
		
		//ação
		boolean result = repository.existsByEmail("luiz@gmail.com");
		
		//verificação
		Assertions.assertThat(result).isFalse();
	}
	
}
