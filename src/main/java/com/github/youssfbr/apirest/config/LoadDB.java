package com.github.youssfbr.apirest.config;

import com.github.youssfbr.apirest.entity.Employee;
import com.github.youssfbr.apirest.entity.Person;
import com.github.youssfbr.apirest.repository.IEmployeeRepository;
import com.github.youssfbr.apirest.repository.IPersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDB {

    @Bean
    public CommandLineRunner commandLineRunner(
            IPersonRepository personRepository,
            IEmployeeRepository employeeRepository
    ) {
        return args -> {
            personRepository.save(new Person(null, "Alisson"));
            personRepository.save(new Person(null, "Link da Silva"));
            employeeRepository.save(new Employee(null, "Maria Silva", "Chefe", "Avenida Silveira Dutra, 1002")) ;
            employeeRepository.save(new Employee(null, "Jonh Dutra", "Tecnico", "Rua JoãoFreire, 231"));
        };
    }

}
