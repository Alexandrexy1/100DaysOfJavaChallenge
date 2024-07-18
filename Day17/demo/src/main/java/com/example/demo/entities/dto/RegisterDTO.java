package com.example.demo.entities.dto;

import com.example.demo.entities.enums.UserRoleEnum;

public record RegisterDTO(String email, String password, UserRoleEnum role) {
}
