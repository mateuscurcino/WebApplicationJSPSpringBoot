package com.code.projectmanagement.controller.rest;

import com.code.projectmanagement.dto.PersonDTO;
import com.code.projectmanagement.model.Person;
import com.code.projectmanagement.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

//Controller that provides a web service to register new people

@RestController
@RequestMapping(value = "/people")
public class PersonRestController {

    @Autowired
    private IPersonService personService;

    @PostMapping
    public ResponseEntity<Person> insert(@Valid @RequestBody PersonDTO personDtoObj) {
        Person person = personService.fromDTO(personDtoObj);
        person = personService.savePerson(person);

        return ResponseEntity.ok().body(person);
    }
}
