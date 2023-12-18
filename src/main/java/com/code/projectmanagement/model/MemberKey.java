package com.code.projectmanagement.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class MemberKey implements Serializable {

    //composite primary key

    @ManyToOne
    @JoinColumn(name = "idProject")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "idPerson")
    private Person person;

    public MemberKey() {
    }

    public MemberKey(Project project, Person person) {
        this.project = project;
        this.person = person;
    }
}
