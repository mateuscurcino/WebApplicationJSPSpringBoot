package com.code.projectmanagement.model.enums;

public enum Status {
    //list of status
    UNDERREVIEW("UNDER REVIEW", true),
    ANALYSISPERFORMED("ANALYSIS PERFORMED", true),
    ANALYSISAPPROVED("ANALYSIS APPROVED", true),
    STARTED("STARTED", false),
    PLANNED("PLANNED", true),
    INPROGRESS("IN PROGRESS", false),
    TERMINATED("TERMINATED", false),
    CANCELED("CANCELED", true);

    private String description;

    private boolean canDeleteProject;

    private Status(String description, boolean canDeleteProject) {
        this.description = description;
        this.canDeleteProject = canDeleteProject;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCanDeleteProject() {
        return canDeleteProject;
    }
}
