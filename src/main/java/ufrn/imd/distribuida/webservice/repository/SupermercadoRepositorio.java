package ufrn.imd.distribuida.webservice.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import ufrn.imd.distribuida.webservice.model.Supermercado;

@Repository
public interface SupermercadoRepositorio extends CrudRepository<Supermercado, Long> {
	Supermercado findByNome(@Param("nome") String nome);
}
