package com.example.demo.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Lancamento;
import com.example.demo.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery{
	
	public Page<Lancamento> filtrar(LancamentoFilter Filter, Pageable pegeable);
	

}
