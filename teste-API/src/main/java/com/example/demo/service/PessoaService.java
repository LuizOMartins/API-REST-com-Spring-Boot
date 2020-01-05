package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired 
	private PessoaRepository pessoaRepository;
	
	public Pessoa ataulizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSource = buscarPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSource , "codigo");
		return pessoaRepository.save(pessoaSource);
	    
	}


	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSource = buscarPessoaPeloCodigo(codigo);
		pessoaSource.setAtivo(ativo);
		pessoaRepository.save(pessoaSource);
					
	}
	
	private Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Optional<Pessoa> pessoaSalva =  pessoaRepository.findById(codigo);
		Pessoa pessoaSource = pessoaSalva.get();
		if (pessoaSalva == null )
			throw new  EmptyResultDataAccessException(1);
			//NoSuchElementException
		return pessoaSource;
	}
	
}
