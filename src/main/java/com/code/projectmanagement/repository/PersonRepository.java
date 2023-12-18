package com.code.projectmanagement.repository;

import com.code.projectmanagement.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAll();

    List<Person> findByManagerTrue();

    Optional<Person> findByIdAndEmployeeTrue(Long id);

    Optional<Person> findByIdAndManagerTrue(Long id);
}
