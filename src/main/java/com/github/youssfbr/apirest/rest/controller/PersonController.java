package com.github.youssfbr.apirest.rest.controller;

import com.github.youssfbr.apirest.entity.Person;
import com.github.youssfbr.apirest.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/persons")
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public List<Person> listAllPeersons() {
        return personService.listAllPersons();
    }

    @GetMapping("/{id}")
    public Person findPersonById(@PathVariable Long id) {
        return personService.findPersonById(id);
    }

}
