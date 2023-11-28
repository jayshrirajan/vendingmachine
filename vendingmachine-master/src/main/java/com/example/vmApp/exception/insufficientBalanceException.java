package com.example.vmApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class insufficientBalanceException extends RuntimeException {

    public insufficientBalanceException(){

        super("Insufficient balance to purchase the item.");
    }

}
