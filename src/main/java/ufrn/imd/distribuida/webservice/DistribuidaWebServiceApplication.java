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

@SpringBootApplication
@EntityScan(basePackages = {"ufrn.imd.distribuida.webservice.model"})
@ComponentScan(basePackages = {"curso.*"})
@EnableJpaRepositories(basePackages = {"ufrn.imd.distribuida.webservice.repository"})
@EnableTransactionManagement
@EnableWebMvc
@RestController
@EnableAutoConfiguration
public class DistribuidaWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistribuidaWebServiceApplication.class, args);
	}

}
