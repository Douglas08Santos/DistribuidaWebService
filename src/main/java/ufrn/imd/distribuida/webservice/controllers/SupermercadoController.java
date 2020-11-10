package ufrn.imd.distribuida.webservice.controllers;

import java.util.ArrayList;
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

/*
 * @GetMapping - Pedido para recuperar dados
 * @PostMapping - Envia dados para processar
 * @PutMapping - Envia dados para atualizar
 * @DeleteMapping - Deleta o registro   
 */
@RestController
@RequestMapping(value="/supermercado")
public class SupermercadoController {
    
    @Autowired
	private SupermercadoRepositorio supermercadoRepositorio;
    
  
    @PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Supermercado> cadastrar(@RequestBody Supermercado supermercado){
		try {
			Supermercado supermercadoSalvo = supermercadoRepositorio.save(supermercado);
			return new ResponseEntity<Supermercado>(supermercadoSalvo, HttpStatus.CREATED);
		} catch (Exception e) {
			Supermercado supermercadoSalvo = new Supermercado();
			return new ResponseEntity<Supermercado>(supermercadoSalvo, HttpStatus.NO_CONTENT);
		} 		
	}
    
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<Supermercado> atualizar(@RequestBody Supermercado supermercado){
    	try {
			Supermercado supermercadoSalvo = supermercadoRepositorio.save(supermercado);
			return new ResponseEntity<Supermercado>(supermercadoSalvo, HttpStatus.OK);
		} catch (Exception e) {
			Supermercado supermercadoSalvo = new Supermercado();
			return new ResponseEntity<Supermercado>(supermercadoSalvo, HttpStatus.NO_CONTENT);
		} 		
    }
    
    @DeleteMapping(value = "/{idSupermercado}", produces = "application/json")
    public ResponseEntity<String> apagar(@PathVariable (value= "idSupermercado") Long idSupermercado){
    	
    	try {
    		supermercadoRepositorio.deleteById(idSupermercado);
		} catch (Exception e) {
			return new ResponseEntity<String>("Supermercado possui produtos ou não existe.",HttpStatus.NO_CONTENT);		 	
		}
    	
    	return new ResponseEntity<String>("",HttpStatus.ACCEPTED);    	
    	
    }
    
	@GetMapping(value = "/{idSupermercado}", produces = "application/json")
    public ResponseEntity<Supermercado> pesquisar(@PathVariable (value= "idSupermercado") Long idSupermercado){
		
		try {
			Optional<Supermercado> supermercado = supermercadoRepositorio.findById(idSupermercado);
			return new ResponseEntity<Supermercado>(supermercado.get(), HttpStatus.FOUND);
		} catch (Exception e) {			
			Supermercado supermercado = new Supermercado();
			return new ResponseEntity<Supermercado>(supermercado, HttpStatus.NOT_FOUND);
		}
    }

	@GetMapping(value = "", produces = "application/json")
    public ResponseEntity<List<Supermercado>> listar(){
    	try {
    		List<Supermercado> supermercados = (List<Supermercado>)supermercadoRepositorio.findAll();        	
        	return new ResponseEntity<List<Supermercado>>(supermercados, HttpStatus.OK);
		} catch (Exception e) {
			List<Supermercado> supermercados = new ArrayList<Supermercado>();        	
        	return new ResponseEntity<List<Supermercado>>(supermercados, HttpStatus.NOT_FOUND);
		}
    	

    }

	

	

}
 