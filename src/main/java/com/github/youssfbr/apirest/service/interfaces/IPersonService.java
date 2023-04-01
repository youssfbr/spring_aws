package com.github.youssfbr.apirest.service.interfaces;

import com.github.youssfbr.apirest.entity.Person;

import java.util.List;

public interface IPersonService {

    List<Person> listAllPersons();
    Person findPersonById(Long id);

}
