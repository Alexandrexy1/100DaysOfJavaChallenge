package com.example.account.DTO;

import com.example.account.entities.enums.UserRole;

public record UserDTO(String name, String email, String password, UserRole role) {
}
