package com.github.youssfbr.apirest;

import com.github.youssfbr.apirest.entity.Person;
import com.github.youssfbr.apirest.repository.IPersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApirestApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(IPersonRepository personRepository) {
		return args -> {
			personRepository.save(new Person(null, "Alisson"));
			personRepository.save(new Person(null, "Link da Silva"));
		};
	}

}
