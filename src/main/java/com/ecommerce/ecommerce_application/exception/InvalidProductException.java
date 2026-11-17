package com.ecommerce.ecommerce_application.exception;


public class InvalidProductException extends RuntimeException {

    public InvalidProductException(String message) {
        super(message);
    }
}