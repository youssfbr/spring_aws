package com.github.youssfbr.apirest.config;

import com.github.youssfbr.apirest.entity.Employee;
import com.github.youssfbr.apirest.entity.OrderHateoas;
import com.github.youssfbr.apirest.entity.Person;
import com.github.youssfbr.apirest.entity.enums.Status;
import com.github.youssfbr.apirest.repository.IEmployeeRepository;
import com.github.youssfbr.apirest.repository.IOrderRepository;
import com.github.youssfbr.apirest.repository.IPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDB {

    private static final Logger log = LoggerFactory.getLogger(LoadDB.class);

    @Bean
    public CommandLineRunner commandLineRunner(
            IPersonRepository personRepository,
            IEmployeeRepository employeeRepository,
            IOrderRepository orderRepository
    ) {
        return args -> {
            personRepository.save(new Person(null, "Alisson"));
            personRepository.save(new Person(null, "Link da Silva"));
            employeeRepository.save(new Employee(null, "Maria Silva", "Chefe", "Avenida Silveira Dutra, 1002")) ;
            employeeRepository.save(new Employee(null, "Jonh Dutra", "Tecnico", "Rua JoãoFreire, 231"));
            orderRepository.save(new OrderHateoas(null, Status.COMPLETED, "review"));
            orderRepository.save(new OrderHateoas(null, Status.IN_PROGRESS, "travel"));
            orderRepository.save(new OrderHateoas(null, Status.IN_PROGRESS, "sale"));
            orderRepository.findAll().forEach(order ->
                    log.info("Preloaded {}", order)
            );
        };
    }

}
