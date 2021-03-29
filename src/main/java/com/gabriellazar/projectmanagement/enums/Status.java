package com.gabriellazar.projectmanagement.enums;

public enum Status {

    OPEN("Open"),IN_PROGRESS("In Progress"),IN_TEST("In Testing"),COMPLETE("Completed");

    private final String value;

    private Status(String value){
        this.value=value;
    }

    public String getValue(){
        return value;
    }
}
