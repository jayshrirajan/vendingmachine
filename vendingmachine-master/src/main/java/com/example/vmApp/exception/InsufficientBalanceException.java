package com.example.vmApp.exception;




public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String message) {

        super(message);
    }
}

