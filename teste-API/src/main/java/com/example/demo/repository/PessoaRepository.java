package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	

}