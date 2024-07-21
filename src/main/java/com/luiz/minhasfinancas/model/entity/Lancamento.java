package com.luiz.minhasfinancas.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "lancamento", schema = "financas")
public class Lancamento {

	private Long id;
	private String descricao;
	private int mes;
	private int ano;
	
}
