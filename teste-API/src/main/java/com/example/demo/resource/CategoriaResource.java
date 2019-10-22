package com.example.demo.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
		return  categoriaRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Categoria> crair(@Valid @RequestBody Categoria categoria,  HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		//setar resposta direto para a URI
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		.buildAndExpand(categoriaSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	/*@GetMapping("/{codigo}")
	public Optional<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		return categoriaRepository.findById(codigo);
	}
	//busca o parametro variavel no final da url e passa como parametro */
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Categoria>> buscarPeloCodigo(@PathVariable Long codigo) {
		 Optional<Categoria> categoria = categoriaRepository.findById(codigo);
		 if (categoria != null)
			return ResponseEntity.ok(categoria);
		else
			return ResponseEntity.notFound().build();
	}
}
