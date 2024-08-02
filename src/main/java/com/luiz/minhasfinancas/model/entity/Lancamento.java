package com.luiz.minhasfinancas.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.luiz.minhasfinancas.model.enums.StatusLancamentoEnum;
import com.luiz.minhasfinancas.model.enums.TipoLancamentoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "lancamento", schema = "financas")
//substitui todas as anotations do Lombok usadas na entidade Usuario
@Data
public class Lancamento {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "mes")
	private int mes;
	
	@Column(name = "ano")
	private int ano;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	//@convert chama um converter de local date para util.date
	@Column(name = "data_cadastro")
	@Convert(converter = Jsr310JpaConverters.class)
	private LocalDate dataCadastro;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "tipo")
	private TipoLancamentoEnum tipo;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "status")
	private StatusLancamentoEnum status;

	
	
	
	
	
	
}
