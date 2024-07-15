package com.example.demo.exceptions;

public class InvalidEmailException extends RuntimeException {
    private final String email;

    public InvalidEmailException(String msg, String email) {
        super(msg);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
