package ufrn.imd.distribuida.webservice.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ufrn.imd.distribuida.webservice.model.Produto;
import ufrn.imd.distribuida.webservice.model.Supermercado;
import ufrn.imd.distribuida.webservice.repository.ProdutoRepositorio;
import ufrn.imd.distribuida.webservice.repository.SupermercadoRepositorio;


@RestController
@RequestMapping(value="/")
public class ProdutoController {
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	@Autowired
	private SupermercadoRepositorio SupermercadoRepositorio;
    
  
    @PostMapping(value = "supermercado/{idSupermercado}/", produces = "application/json")
	public void cadastrar(@PathVariable("idSupermercado") Integer idSupermercado, @RequestBody Produto produto){
		long idSuper = (long) idSupermercado;
		Optional<Supermercado> supermercado = SupermercadoRepositorio.findById(idSuper);
		produto.setSupermercado(supermercado.get());
    	
    	//Produto produtoSalvo = produtoRepositorio.save(produto);
    	produtoRepositorio.save(produto);
		
		//return new ResponseEntity<Produto>(produtoSalvo, HttpStatus.OK);
	}
    
    @PutMapping(value = "supermercado/produto/", produces = "application/json")
    public void atualizar(@RequestBody Produto produto){
    	//Produto produtoSalvo = produtoRepositorio.save(produto);
    	produtoRepositorio.save(produto);
		//return new ResponseEntity<Produto>(produtoSalvo, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "supermercado/produto/{id}", produces = "application/json")
    public void apagar(@PathVariable (value= "id") Long id){
    	//Optional<Produto> produto = produtoRepositorio.findById(id);
    	produtoRepositorio.deleteById(id);
    	
    	//return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);
    }
	@GetMapping(value = "supermercado/produto/{id}", produces = "application/json")
    public ResponseEntity<Produto> pesquisar(@PathVariable (value= "id") Long id){
    	
    	Optional<Produto> produto = produtoRepositorio.findById(id);
    	
    	return new ResponseEntity<Produto>(produto.get(), HttpStatus.OK);

    }

	@GetMapping(value = "supermercado/produtos", produces = "application/json")
    public ResponseEntity<List<Produto>> listar(){
    	
    	List<Produto> produtos = (List<Produto>)produtoRepositorio.findAll();
    	
    	return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);

    }

}
