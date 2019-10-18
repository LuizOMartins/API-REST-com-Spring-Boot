package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Categoria;

//JpaRepository: ja possui CRUD feito e diversos rescuros. não precisa de implamentação o propria JPA ja faz isso em
//tempo de execução
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
