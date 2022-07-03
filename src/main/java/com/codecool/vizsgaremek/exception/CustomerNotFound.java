package com.codecool.vizsgaremek.exception;

public class CustomerNotFound extends RuntimeException {
    public CustomerNotFound() {
    }
    public CustomerNotFound(String message) {
        super(message);
    }
}
