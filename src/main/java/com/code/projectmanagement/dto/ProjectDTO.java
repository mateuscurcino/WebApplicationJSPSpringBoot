package com.code.projectmanagement.dto;

import com.code.projectmanagement.model.Project;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class ProjectDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 200, message = "Name must have a maximum of 200 characters")
    private String name;

    @NotNull(message = "Manager is mandatory")
    private Long manager;

    private Date startDate;

    private Date expectedEndDate;

    private Date endDate;

    @Size(max = 5000, message = "Description must have a maximum of 5000 characters")
    private String description;

    private Float budget;

    private String status;

    private String risk;

    public ProjectDTO() {
    }

    public ProjectDTO(Long id, String name, Long manager, Date startDate, Date expectedEndDate, Date endDate,
                      String description, Float budget, String status, String risk) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.startDate = startDate;
        this.expectedEndDate = expectedEndDate;
        this.endDate = endDate;
        this.description = description;
        this.budget = budget;
        this.status = status;
        this.risk = risk;
    }

    public ProjectDTO(Project obj) {
        id = obj.getId();
        name = obj.getName();
        manager = obj.getManager().getId();
        startDate = obj.getStartDate();
        expectedEndDate = obj.getExpectedEndDate();
        endDate = obj.getEndDate();
        description = obj.getDescription();
        budget = obj.getBudget();
        status = obj.getStatus();
        risk = obj.getRisk();
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

    public Long getManager() {
        return manager;
    }

    public void setManager(Long manager) {
        this.manager = manager;
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

    public Float getBudget() {
        return budget;
    }

    public void setBudget(Float budget) {
        this.budget = budget;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
}
