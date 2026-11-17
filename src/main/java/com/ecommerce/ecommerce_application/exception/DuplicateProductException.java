package com.ecommerce.ecommerce_application.exception;




public class DuplicateProductException extends RuntimeException {

    public DuplicateProductException(String message) {
        super(message);
    }
}
