package com.code.projectmanagement.model.enums;

public enum Position {
    //list of positions
    EMPLOYEE("EMPLOYEE"),
    MANAGER("MANAGER");

    private String description;

    private Position(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
