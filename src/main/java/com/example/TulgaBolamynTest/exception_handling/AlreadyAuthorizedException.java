package com.example.TulgaBolamynTest.exception_handling;

public class AlreadyAuthorizedException extends RuntimeException  {
    public AlreadyAuthorizedException(String message) {
        super(message);
    }
}
