package br.com.ifpe.oxefood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //vai fazer com q os objetos  quando forem persistível no banco de dados, o jpa preencha automaticamente os campos de auditoria(campos da entidade auditável)
public class OxefoodApiGuilhermeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OxefoodApiGuilhermeApplication.class, args);
	}

}
