package com.sg.guessnumber.controller;

import com.sg.guessnumber.dao.guessNumberPersistenceException;
import com.sg.guessnumber.entity.Game;
import com.sg.guessnumber.entity.Round;
import com.sg.guessnumber.service.GuessNumberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jose
 */
@RestController
public class guessNumberController {
    
    @Autowired
    GuessNumberService service;
    
    @PostMapping("/game")
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame() {
        
        return service.addGame();
    }
    
    @PostMapping("/guess/{id}/{guess}")
    @ResponseStatus(HttpStatus.CREATED)
    public Round guess(@PathVariable("id") Integer id, @PathVariable("guess") String guess) throws guessNumberPersistenceException{
        Game game = service.getGame(id);
        return service.makeGuess(game, guess);
    }
    
    @GetMapping("/game/{id}")
    public Game getGame(@PathVariable("id") Integer id) {
        return service.getGame(id);
    }
    
    @GetMapping("/game/list")
    public List<Game> getListOfGames() {
        return service.getGames();
    }
    
    @GetMapping("round/{id}")
    public List<Round> getListOfRounds(@PathVariable("id") Integer id) {
        Game game = service.getGame(id);
        return service.rounds(game);
    }
}
