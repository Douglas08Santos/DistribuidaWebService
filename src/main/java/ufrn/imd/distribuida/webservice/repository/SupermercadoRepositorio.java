package ufrn.imd.distribuida.webservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ufrn.imd.distribuida.webservice.model.Supermercado;

@Repository
public interface SupermercadoRepositorio extends CrudRepository<Supermercado, Long> {

}
