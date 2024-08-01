package com.example.account.entities.enums;

public enum UserRole {
    USER("user"),
    GUEST("guest");

    private String role;

    private UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
