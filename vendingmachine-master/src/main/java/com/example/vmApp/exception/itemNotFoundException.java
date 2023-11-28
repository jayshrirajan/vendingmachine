package com.example.vmApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class itemNotFoundException extends RuntimeException {

    public itemNotFoundException() {

        super("The selected item is not available in the vending machine.");

    }
}
