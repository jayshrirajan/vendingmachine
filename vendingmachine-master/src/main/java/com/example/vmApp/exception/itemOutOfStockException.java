package com.example.vmApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class itemOutOfStockException extends RuntimeException {
    public itemOutOfStockException() {

        super("Selected Item is out of Stock in the Vending Machine.");
    }
}
