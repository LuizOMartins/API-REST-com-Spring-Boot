package com.example.demo.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.event.RecursoCriadoEvent;
import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	//Informa ao Spring que  a instancia desse objeto precisa ser injetado, ser ser necessária instancia
	// do objeto na "aplicação"
	@Autowired //anotação para injeção
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public List<Categoria> listar(){
		return categoriaRepository.findAll();
	}
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Categoria> crair(@Valid @RequestBody Categoria categoria,  HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
		
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Categoria>> buscarPeloCodigo(@PathVariable Long codigo) {
		 Optional<Categoria> categoria = categoriaRepository.findById(codigo);
		 if (categoria != null)
			return ResponseEntity.ok(categoria);
		else
			return ResponseEntity.notFound().build();
	}
}
