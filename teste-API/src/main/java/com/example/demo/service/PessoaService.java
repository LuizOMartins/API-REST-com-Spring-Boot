package com.example.demo.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pessoa;
import com.example.demo.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired 
	private PessoaRepository pessoaRepository;
	
	public Pessoa ataulizar(Long codigo, Pessoa pessoa) {
		Optional<Pessoa> pessoaSalva =  pessoaRepository.findById(codigo);
		Pessoa pessoaSource = pessoaSalva.get();
		if (pessoaSalva == null )
			throw new  NoSuchElementException();
		BeanUtils.copyProperties(pessoa, pessoaSource , "codigo");
		return pessoaRepository.save(pessoaSource);
	    
	}
}
