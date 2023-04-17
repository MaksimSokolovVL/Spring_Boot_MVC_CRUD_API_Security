package com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity;

public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    GUEST("ROLE_GUEST");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

