/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessnumber.controller;

import com.sg.guessnumber.dao.guessNumberPersistenceException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Jose
 */
@ControllerAdvice
@RestController
public class guessControllerExceptionHandler {
    
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class) //this is because data cant be null
    public final ResponseEntity<Error> handleSqlException(
            SQLIntegrityConstraintViolationException ex,
            WebRequest request) {
        String MESSAGE = ex.getMessage();
        Error err = new Error();
        err.setMessage(MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(guessNumberPersistenceException.class) //this is because data cant be null
    public final ResponseEntity<Error> handleSqlException(
            guessNumberPersistenceException ex,
            WebRequest request) {
        String MESSAGE = ex.getMessage();
        Error err = new Error();
        err.setMessage(MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
