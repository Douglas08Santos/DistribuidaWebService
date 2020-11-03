package ufrn.imd.distribuida.webservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ufrn.imd.distribuida.webservice.model.SupermercadoProduto;

@Repository
public interface SupermercadoProdutoRepositorio extends CrudRepository<SupermercadoProduto, Long>{

}
