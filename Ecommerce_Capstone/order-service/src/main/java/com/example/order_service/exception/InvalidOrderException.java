package com.example.order_service.exception;

public class InvalidOrderException extends Exception {

    public InvalidOrderException(String message) {
        super(message);
    }
}
