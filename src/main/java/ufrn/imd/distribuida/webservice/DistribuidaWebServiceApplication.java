package ufrn.imd.distribuida.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//Identifica que é uma aplicação spring
@SpringBootApplication(scanBasePackages = "DistribuidaWebServiceApplication")
//Procura as entidade na pasta passada como argumento, reponsavel por criar as tabelas no banco
@EntityScan(basePackages = {"ufrn.imd.distribuida.webservice.model"})
//procura tudo para configurar
@ComponentScan(basePackages = {"ufrn.*"})
//Onde será persistido os dados
@EnableJpaRepositories(basePackages = {"ufrn.imd.distribuida.webservice.repository"})
//Recursos de transação
@EnableTransactionManagement
//MVC
@EnableWebMvc
//REST
@RestController
//Configura as anotações+
@EnableAutoConfiguration
public class DistribuidaWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistribuidaWebServiceApplication.class, args);
	}
	

}
