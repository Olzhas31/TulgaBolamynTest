package com.example.TulgaBolamynTest.exception_handling;

public final class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(final String message) {
        super(message);
    }
}
