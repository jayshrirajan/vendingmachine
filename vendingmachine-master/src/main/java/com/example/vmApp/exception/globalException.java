package com.example.vmApp.exception;

import org.springframework.http.HttpStatus;

public class globalException {
    private final  String message;
    private final  HttpStatus status;

    public globalException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }


}
