package com.service.api.handler;

import com.service.domain.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Serviço de respota de tratamento de erros Global
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseException> handleUserNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(new ResponseException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND,ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> handleGenericException(Exception ex) {
        return new ResponseEntity<>(new ResponseException(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR,"Ocorreu um erro inesperado!"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
