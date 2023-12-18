package com.code.projectmanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must have a maximum of 100 characters")
    private String name;

    private Date birthdate;

    @Size(max = 14, message = "CPF must have a maximum of 14 characters")
    private String cpf;

    private boolean employee;

    private boolean manager;

    public Person() {
        super();
    }

    public Person(Long id, String name, Date birthdate, String cpf, boolean employee, boolean manager) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.cpf = cpf;
        this.employee = employee;
        this.manager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() && isEmployee() == person.isEmployee() && isManager() == person.isManager() && Objects.equals(getName(), person.getName()) && Objects.equals(getBirthdate(), person.getBirthdate()) && Objects.equals(getCpf(), person.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBirthdate(), getCpf(), isEmployee(), isManager());
    }
}
