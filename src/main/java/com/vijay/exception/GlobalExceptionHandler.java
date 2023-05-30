package com.vijay.exception;

import com.vijay.modal.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransactionException.class)
    public ErrorResponse handleException(TransactionException transactionException) {
        return ErrorResponse.builder().errorResponse(transactionException.getErrorMessage()).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handleException(RuntimeException transactionException) {
        return ErrorResponse.builder().errorResponse(transactionException.getLocalizedMessage()).build();
    }
}
