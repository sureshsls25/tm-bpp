package com.ms.bpp.entity;

public enum UserRoles {
    USER("Users"),
    ADMIN("Admin");

    private final String value;

    UserRoles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
