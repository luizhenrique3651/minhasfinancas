package com.luiz.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luiz.minhasfinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
