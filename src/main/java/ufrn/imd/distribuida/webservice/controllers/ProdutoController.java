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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import ufrn.imd.distribuida.webservice.model.ListaCompras;
import ufrn.imd.distribuida.webservice.model.Produto;
import ufrn.imd.distribuida.webservice.model.Supermercado;
import ufrn.imd.distribuida.webservice.repository.ProdutoRepositorio;
import ufrn.imd.distribuida.webservice.repository.SupermercadoRepositorio;

/*
 * @GetMapping - Pedido para recuperar dados
 * @PostMapping - Envia dados para processar
 * @PutMapping - Envia dados para atualizar
 * @DeleteMapping - Deleta o registro   
 */
@RestController
@RequestMapping(value="/")
public class ProdutoController {
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	@Autowired
	private SupermercadoRepositorio SupermercadoRepositorio;
    
  
    @PostMapping(value = "supermercado/{idSupermercado}", produces = "application/json")
	public ResponseEntity<Supermercado> cadastrar(@PathVariable("idSupermercado") Integer idSupermercado, @RequestBody Produto produto){
		long idSuper = (long) idSupermercado;
		Optional<Supermercado> supermercado = SupermercadoRepositorio.findById(idSuper);
		produto.setSupermercado(supermercado.get()); 
    	
    	produtoRepositorio.save(produto);
    	supermercado = SupermercadoRepositorio.findById(idSuper);
		return new ResponseEntity<Supermercado>(supermercado.get(), HttpStatus.OK);
	}
    
    @PutMapping(value = "supermercado/{idSupermercado}", produces = "application/json")
    public ResponseEntity<Supermercado> atualizar(@PathVariable("idSupermercado") Integer idSupermercado, @RequestBody Produto produto){
    	long idSuper = (long) idSupermercado;
		Optional<Supermercado> supermercado = SupermercadoRepositorio.findById(idSuper);
		produto.setSupermercado(supermercado.get());
    	produtoRepositorio.save(produto);
    	supermercado = SupermercadoRepositorio.findById(idSuper);
		return new ResponseEntity<Supermercado>(supermercado.get(), HttpStatus.OK);
    }
    
	@DeleteMapping(value = "supermercado/{idSupermercado}/produto/{idProduto}", produces = "application/json")
    public void apagarPorId(@PathVariable(value = "idSupermercado") Long idSupermercado, @PathVariable(value= "idProduto") Long idProduto){
    	Long idSuper = (long) idSupermercado;
		Optional<Supermercado> supermercado = SupermercadoRepositorio.findById(idSuper);
    	
    	for (Produto p: supermercado.get().getProduto()) {
			if (p.getIdProduto() == idProduto) {
				produtoRepositorio.deleteById(idProduto);
			}
		}    
    	
    	
    }
    //															?nome=produtoNome
    @DeleteMapping(value = "supermercado/{idSupermercado}/produto", produces = "application/json")
    public void apagarPorNome(@PathVariable(value = "idSupermercado") Long idSupermercado, @RequestParam(value= "nome") String nome){
    	Optional<Supermercado> supermercado = SupermercadoRepositorio.findById((Long)idSupermercado);
    	List<Produto> produtos = supermercado.get().getProduto();
    	for (Produto p: produtos) {
			if (p.getNome().equals(nome)) {
				produtoRepositorio.deleteById(p.getIdProduto());
			}
		}    
    	
    }
    
    
	@GetMapping(value = "produto/{idProduto}", produces = "application/json")
    public ResponseEntity<Supermercado> pesquisarPorId(@PathVariable(value= "idProduto") Long idProduto){
    	
    	Optional<Produto> produto = produtoRepositorio.findById(idProduto);
    	Optional<Supermercado> supermercado = SupermercadoRepositorio.findById(produto.get().getSupermercado().getIdSupermercado());
    	
    	
    	return new ResponseEntity<Supermercado>(supermercado.get(), HttpStatus.OK);

    }
	//														  ?nome=<nome produto>
	@GetMapping(value = "supermercado/{idSupermercado}/produto", produces = "application/json")
    public ResponseEntity<List<Produto>> pesquisarPorNome(@PathVariable(value = "idSupermercado") Long idSupermercado, @RequestParam(value= "nome") String nome){
    	
		Optional<Supermercado> supermercado = SupermercadoRepositorio.findById(idSupermercado);
    	List<Produto> produtos = supermercado.get().getProduto(); 
    	List<Produto> resultado = new ArrayList<Produto>();
    	for (Produto p: produtos) {
			if (p.getNome().equals(nome)) {
				resultado.add(p);
				return new ResponseEntity<List<Produto>>(resultado, HttpStatus.OK);
			}
		}
    	
    	return new ResponseEntity<List<Produto>>(resultado, HttpStatus.NOT_FOUND);
    	

    }
	
	//	 
	@GetMapping(value = "consulta", produces = "application/json")
	public ResponseEntity<List<Supermercado>> pesquisarMenorPreco(@RequestBody ListaCompras listaCompras){
		
		List<Supermercado> supermercados = (List<Supermercado>) SupermercadoRepositorio.findAll();
		List<Supermercado> resultado = new ArrayList<Supermercado>();
		Double menorPreco;
		Produto produtoMenor = new Produto();
		Supermercado supermercadomenorPreco = new Supermercado();
		for(String i: listaCompras.getLista()) {
			menorPreco = Double.MAX_VALUE;
			for (Supermercado s: supermercados) {
				for(Produto p: s.getProduto()) {
					if(resultado.contains(s) == false && p.getNome().equals(i)){
						Supermercado sup = new Supermercado();
						sup.setIdSupermercado(s.getIdSupermercado());
						sup.setNome(s.getNome());
						resultado.add(sup);
						
					}	
					
					if (p.getNome().equals(i) && p.getPreco() < menorPreco) {
						menorPreco = p.getPreco();
						produtoMenor = p;
						supermercadomenorPreco = s;						
					}					
				}			
			}
			int index = resultado.indexOf(supermercadomenorPreco);
			resultado.get(index).getProduto().add(produtoMenor);
			
		}
		
		return new ResponseEntity<List<Supermercado>>(resultado, HttpStatus.OK);
	
	}
	
	//Listar Produtos de um Supermercado
	@GetMapping(value = "supermercado/{idSupermercado}/produtos", produces = "application/json")
    public ResponseEntity<List<Produto>> listarProdutosSupermercado(@PathVariable(value = "idSupermercado") Long idSupermercado){
    	
    	Optional<Supermercado> supermercado = SupermercadoRepositorio.findById(idSupermercado);
    	List<Produto> produtos = supermercado.get().getProduto();
    	return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);

    }

}
