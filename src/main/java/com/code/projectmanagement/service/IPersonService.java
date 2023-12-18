package com.code.projectmanagement.service;

import com.code.projectmanagement.dto.PersonDTO;
import com.code.projectmanagement.model.Person;

import java.util.List;

public interface IPersonService {
    List<Person> getManagers();

    Person savePerson(Person person);

    Person fromDTO(PersonDTO objDto);
}
