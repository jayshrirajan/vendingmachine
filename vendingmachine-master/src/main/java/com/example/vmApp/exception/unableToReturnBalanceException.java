package com.example.vmApp.exception;

public class unableToReturnBalanceException extends RuntimeException {

    public unableToReturnBalanceException() {

        super("The vending machine is unable to return the balance.");
    }
}
