package com.code.projectmanagement.dto;

public class ProjectRestDTO {

    private String status;

    private String risk;

    public ProjectRestDTO(String status, String risk) {
        this.status = status;
        this.risk = risk;
    }

    public ProjectRestDTO() {
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
