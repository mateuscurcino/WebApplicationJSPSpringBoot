package com.code.projectmanagement.model.enums;

public enum Risk {
    //list of risks
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");

    private String description;

    private Risk(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
