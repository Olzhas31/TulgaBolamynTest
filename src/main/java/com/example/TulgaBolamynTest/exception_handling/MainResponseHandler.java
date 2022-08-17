package com.example.TulgaBolamynTest.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainResponseHandler {

    @ExceptionHandler(value = {MyException.class, UserAlreadyExistException.class})
    public String handleException(Exception e){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return "404";
//        return new ResponseEntity<>(e.getMessage(), status);
    }
}
