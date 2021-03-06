/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessnumber.controller;

import org.springframework.dao.DataIntegrityViolationException;
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
public class guessNumberDataIntegrityException {
    private static final String MESSAGE = "Could not process your guess. "
            + "Please ensure your guess has 4 numbers.";
    
    @ExceptionHandler(DataIntegrityViolationException.class) //this is because data cant be null
    public final ResponseEntity<Error> handleSqlException(
            DataIntegrityViolationException ex,
            WebRequest request) {
        Error err = new Error();
        err.setMessage(MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
