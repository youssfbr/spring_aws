package com.github.youssfbr.apirest.service;

import com.github.youssfbr.apirest.entity.Person;
import com.github.youssfbr.apirest.repository.IPersonRepository;
import com.github.youssfbr.apirest.service.interfaces.IPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService implements IPersonService {

    private final IPersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Person> listAllPersons() {
        return personRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Person findPersonById(Long id) {
        return personRepository.findById(id).get();
    }

}
