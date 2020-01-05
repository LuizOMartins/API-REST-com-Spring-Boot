package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>  {

}
