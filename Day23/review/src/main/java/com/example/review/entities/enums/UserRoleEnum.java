package com.example.review.entities.enums;

public enum UserRoleEnum {
    ADMIN("admin"),
    USER("user");

    private String role;

    private UserRoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}