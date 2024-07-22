package com.example.review.DTO;

import com.example.review.entities.enums.UserRoleEnum;

public record RegisterDTO(String username, String password, UserRoleEnum role) {
    
}
