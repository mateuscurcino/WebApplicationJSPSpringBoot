package com.code.projectmanagement.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PersonDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must have a maximum of 100 characters")
    private String name;

    @NotBlank(message = "Position is mandatory")
    private String position;

    public PersonDTO() {
    }

    public PersonDTO(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
