package br.com.teste.processadora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class TesteProcessadoraApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteProcessadoraApplication.class, args);
	}
}
