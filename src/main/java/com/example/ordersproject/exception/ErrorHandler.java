package com.example.ordersproject.exception;

import com.example.ordersproject.model.enums.ExceptionConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.ordersproject.model.enums.ExceptionConstants.UNEXPECTED_EXCEPTION;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
@Slf4j
@RestControllerAdvice //bu classda ne varsa error handlerimiz budu
public class ErrorHandler {
    @ExceptionHandler(Exception.class) //hansi classa bagli oldugunu bildirir
    @ResponseStatus(INTERNAL_SERVER_ERROR) //hansi statusla isteyirse onu qaytarsin
    public ErrorResponse handle(Exception ex){
        log.error("Exception", ex);
        return new ErrorResponse(UNEXPECTED_EXCEPTION.getCode(), UNEXPECTED_EXCEPTION.getMessage() );

    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException ex){
        log.error("NotFoundException", ex);
        return new ErrorResponse(ex.getCode(), ex.getMessage());
    }
}
