package com.code.projectmanagement.service;

import com.code.projectmanagement.dto.PersonDTO;
import com.code.projectmanagement.model.Person;
import com.code.projectmanagement.model.enums.Position;
import com.code.projectmanagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getManagers() {
        return personRepository.findByManagerTrue();
    }

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person fromDTO(PersonDTO objDto) {
        Person person = new Person();

        person.setName(objDto.getName());

        String position = objDto.getPosition();

        //position must have two value options: EMPLOYEE or MANAGER
        if (position != null) {
            position = position.trim().toUpperCase();

            person.setEmployee(position.equals(Position.EMPLOYEE.getDescription()));
            person.setManager(position.equals(Position.MANAGER.getDescription()));
        }

        return person;
    }
}
