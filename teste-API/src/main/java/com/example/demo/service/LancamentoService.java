package com.example.demo.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Lancamento;
import com.example.demo.model.Pessoa;
import com.example.demo.repository.LancamentoRepository;
import com.example.demo.repository.PessoaRepository;
import com.example.demo.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired 
	private LancamentoRepository lancamentoRepository;
	
	public Lancamento salvar(@Valid Lancamento lancamento) {
		
		Optional<Pessoa> pessoa =  pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		
		if(pessoa == null ||  pessoa.get().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		return  lancamentoRepository.save(lancamento);
						
	}

}
