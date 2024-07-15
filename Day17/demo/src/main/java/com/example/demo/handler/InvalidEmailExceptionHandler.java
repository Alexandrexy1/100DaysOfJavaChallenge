package com.example.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.error.ErrorEmail;
import com.example.demo.exceptions.InvalidEmailException;

@ControllerAdvice
public class InvalidEmailExceptionHandler {
    
    @ResponseBody
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(InvalidEmailException.class)
    public ErrorEmail handler(InvalidEmailException e) {
        return new ErrorEmail(e.getMessage(), e.getEmail());
    }
}
