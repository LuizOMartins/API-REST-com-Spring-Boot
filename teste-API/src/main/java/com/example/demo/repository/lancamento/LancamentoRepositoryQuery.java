package com.example.demo.repository.lancamento;

import java.util.List;

import com.example.demo.model.Lancamento;
import com.example.demo.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery{
	
	public List<Lancamento> filtrar(LancamentoFilter Filter);
	

}
