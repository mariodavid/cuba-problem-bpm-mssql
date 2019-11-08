package com.company.cpbm.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum TaskState implements EnumClass<String> {

    NEW("NEW"),
    CONFIRMED("CONFIRMED"),
    CLOSED("CLOSED");

    private String id;

    TaskState(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TaskState fromId(String id) {
        for (TaskState at : TaskState.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}