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


import ufrn.imd.distribuida.webservice.model.Supermercado;
import ufrn.imd.distribuida.webservice.repository.SupermercadoRepositorio;


@RestController
@RequestMapping(value="/supermercado")
public class SupermercadoController {
    
    @Autowired
	private SupermercadoRepositorio supermercadoRepositorio;
    
	@GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Supermercado> getSupermercado(@PathVariable (value= "id") Long id){
    	
    	Optional<Supermercado> supermercado = supermercadoRepositorio.findById(id);
    	
    	return new ResponseEntity<Supermercado>(supermercado.get(), HttpStatus.OK);

    }

	@GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Supermercado>> listarSupermercados(@PathVariable (value= "id") Long id){
    	
    	List<Supermercado> supermercados = (List<Supermercado>)supermercadoRepositorio.findAll();
    	
    	return new ResponseEntity<List<Supermercado>>(supermercados, HttpStatus.OK);

    }

	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Supermercado> cadastrar(@RequestBody Supermercado supermercado){
		Supermercado supermercadoSalvo = supermercadoRepositorio.save(supermercado);
		
		return new ResponseEntity<Supermercado>(supermercadoSalvo, HttpStatus.OK);
	}

	

}
 