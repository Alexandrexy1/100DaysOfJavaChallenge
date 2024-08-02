package com.example.account.dto;

import com.example.account.entities.enums.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {
}
