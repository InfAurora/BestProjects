/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessnumber.dao;

/**
 *
 * @author Jose
 */
public class guessNumberPersistenceException extends Exception{
    public guessNumberPersistenceException(String message) {
        super(message);
    }
    
    public guessNumberPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
