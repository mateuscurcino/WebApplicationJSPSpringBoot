package com.code.projectmanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 200, message = "Name must have a maximum of 200 characters")
    private String name;

    private Date startDate;

    private Date expectedEndDate;

    private Date endDate;

    @Size(max = 5000, message = "Description must have a maximum of 5000 characters")
    private String description;

    @Size(max = 45, message = "Status must have a maximum of 45 characters")
    private String status;

    private Float budget;

    @Size(max = 45, message = "Risk must have a maximum of 45 characters")
    private String risk;

    @NotNull(message = "Manager is mandatory")
    @ManyToOne
    @JoinColumn(name = "idManager")
    private Person manager;

    public Project() {
        super();
    }

    public Project(Long id, String name, Date startDate, Date expectedEndDate, Date endDate, String description,
                   String status, Float budget, String risk, Person manager) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.endDate = endDate;
        this.description = description;
        this.status = status;
        this.budget = budget;
        this.risk = risk;
        this.manager = manager;
    }

    public Project(Long id, String name, Date startDate, Date expectedEndDate, Date endDate, String description,
                   Float budget, Person manager) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.endDate = endDate;
        this.description = description;
        this.budget = budget;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpectedEndDate() {
        return expectedEndDate;
    }

    public void setExpectedEndDate(Date expectedEndDate) {
        this.expectedEndDate = expectedEndDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getBudget() {
        return budget;
    }

    public void setBudget(Float budget) {
        this.budget = budget;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }
}