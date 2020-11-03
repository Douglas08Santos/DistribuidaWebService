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

import ufrn.imd.distribuida.webservice.model.Supermercado;
import ufrn.imd.distribuida.webservice.repository.SupermercadoRepositorio;


@RestController
@RequestMapping(value="/supermercado")
public class SupermercadoController {
    
    @Autowired
	private SupermercadoRepositorio supermercadoRepositorio;
    
  
    @PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Supermercado> cadastrar(@RequestBody Supermercado supermercado){
		Supermercado supermercadoSalvo = supermercadoRepositorio.save(supermercado);
		
		return new ResponseEntity<Supermercado>(supermercadoSalvo, HttpStatus.OK);
	}
    
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Supermercado> atualizar(@RequestBody Supermercado supermercado){
    	Supermercado supermercadoSalvo = supermercadoRepositorio.save(supermercado);
		
		return new ResponseEntity<Supermercado>(supermercadoSalvo, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/{idSupermercado}", produces = "application/json")
    public ResponseEntity<Supermercado> apagar(@PathVariable (value= "idSupermercado") Long idSupermercado){
    	Optional<Supermercado> supermercado = supermercadoRepositorio.findById(idSupermercado);
    	supermercadoRepositorio.deleteById(idSupermercado);
    	
    	return new ResponseEntity<Supermercado>(supermercado.get(), HttpStatus.OK);
    }
	@GetMapping(value = "/{idSupermercado}", produces = "application/json")
    public ResponseEntity<Supermercado> pesquisar(@PathVariable (value= "idSupermercado") Long idSupermercado){
    	
    	Optional<Supermercado> supermercado = supermercadoRepositorio.findById(idSupermercado);
    	
    	return new ResponseEntity<Supermercado>(supermercado.get(), HttpStatus.OK);

    }
	
	@GetMapping(value = "/?nome={nome}", produces = "application/json")
    public ResponseEntity<Supermercado> pesquisarPorNome(@PathVariable (value= "nome") String nome){
    	
    	Supermercado supermercado = supermercadoRepositorio.findByNome(nome);
    
    	return new ResponseEntity<Supermercado>(supermercado, HttpStatus.OK);

    }

	@GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Supermercado>> listar(){
    	
    	List<Supermercado> supermercados = (List<Supermercado>)supermercadoRepositorio.findAll();
    	
    	return new ResponseEntity<List<Supermercado>>(supermercados, HttpStatus.OK);

    }

	

	

}
 