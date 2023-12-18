package com.code.projectmanagement.dto;

import javax.validation.constraints.NotNull;

public class MemberDTO {

    @NotNull(message = "Project is mandatory")
    private Long project;

    @NotNull(message = "Person is mandatory")
    private Long person;

    public MemberDTO() {
    }

    public MemberDTO(Long project, Long person) {
        this.project = project;
        this.person = person;
    }

    public Long getProject() {
        return project;
    }

    public void setProject(Long project) {
        this.project = project;
    }

    public Long getPerson() {
        return person;
    }

    public void setPerson(Long person) {
        this.person = person;
    }
}
