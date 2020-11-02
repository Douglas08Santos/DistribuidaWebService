package ufrn.imd.distribuida.webservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ufrn.imd.distribuida.webservice.model.Produto;

@Repository
public interface ProdutoRepositorio extends CrudRepository<Produto, Long> {
	
}
