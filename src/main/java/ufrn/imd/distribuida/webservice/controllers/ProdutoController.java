package ufrn.imd.distribuida.webservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ufrn.imd.distribuida.webservice.model.Produto;
import ufrn.imd.distribuida.webservice.repository.ProdutoRepositorio;


@RestController
@RequestMapping(value="/produto")
public class ProdutoController {
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Produto> pesquisar(@PathVariable (value= "id") Long id){
		
		Optional<Produto> produto = produtoRepositorio.findById(id);
		
		return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
	
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Produto>> listar(){
		
		List<Produto> produtos = (List<Produto>)produtoRepositorio.findAll();
		
		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto){
		Produto produtoSalvo = produtoRepositorio.save(produto);
		
		return new ResponseEntity<Produto>(produtoSalvo, HttpStatus.OK);
	}
}
