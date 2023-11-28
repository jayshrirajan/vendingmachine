package com.example.vmApp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import springfox.documentation.annotations.ApiIgnore;

@ControllerAdvice
public class globalExceptionHandler {
    @ExceptionHandler(itemOutOfStockException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleItemOutOfStockException(itemOutOfStockException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }





//    @ExceptionHandler(insufficientBalanceException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<String> handleInsufficientBalanceException(insufficientBalanceException ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(ex.getMessage());
//    }
//
//    @ExceptionHandler(itemNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<String> handleItemNotFoundException(itemNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(ex.getMessage());
//    }
//
//    @ExceptionHandler(unableToReturnBalanceException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<String> handleUnableToReturnBalanceException(Exception ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(ex.getMessage());
//    }

}
