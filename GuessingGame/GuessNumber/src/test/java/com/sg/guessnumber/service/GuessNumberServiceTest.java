/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessnumber.service;

import com.sg.guessnumber.dao.GameDaoDB;
import com.sg.guessnumber.entity.Game;
import com.sg.guessnumber.entity.Round;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Jose
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuessNumberServiceTest {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    GuessNumberService service;
    
    @Autowired
    GameDaoDB gameDao;

    public GuessNumberServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        jdbc.update("DELETE FROM Round WHERE gameId != 1 OR roundId != 1");
        jdbc.update("DELETE FROM Game WHERE gameId != 1");
    }

    @After
    public void tearDown() {
        jdbc.update("DELETE FROM Round WHERE gameId != 1 OR roundId != 1");
        jdbc.update("DELETE FROM Game WHERE gameId != 1");
    }

    @Test
    public void testAdd() {
        Game game = service.addGame();
        //game.setGameId(3);
        Game testGame = new Game();
        testGame.setGameId(game.getGameId());
        testGame.setAnswer("****");
        testGame.setStatus(false);
        assertEquals(game, testGame);
    }

    @Test
    public void testGetGame() {
        Game game = service.getGame(1);
        Game testGame = new Game();
        testGame.setGameId(game.getGameId());
        testGame.setStatus(false);
        testGame.setAnswer("****");
        assertEquals(game, testGame);
    }

    @Test
    public void testGetAllGames() {
        List<Game> games = service.getGames();
        games.get(0).setRounds(null);
        Game game = new Game();
        game.setGameId(games.get(0).getGameId());
        game.setStatus(false);
        game.setAnswer("****");
        game.setRounds(null);
        assertTrue(games.contains(game));
        assertEquals(games.size(), 1);
    }

    @Test
    public void testMakeGuess() throws Exception{
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(false);
        Game gameD = gameDao.addGame(game);
        game.setGameId(gameDao.getAllGames().get(1).getGameId());
        
        Round guess = service.makeGuess(gameD, "1547");
        List<Round> rounds = service.rounds(game);
        assertTrue(guess.getExact() == 1);
        assertTrue(guess.getPartial() == 1);
        assertEquals(guess, rounds.get(0));
    }
    
    @Test
    public void testMakeGuessExact() throws Exception{
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(false);
        Game gameD = gameDao.addGame(game);
        game.setGameId(gameDao.getAllGames().get(1).getGameId());
        
        Round guess = service.makeGuess(gameD, "1234");
        List<Round> rounds = service.rounds(game);
        assertTrue(guess.getExact() == 4);
        assertTrue(guess.getPartial() == 0);
        assertEquals(guess, rounds.get(0));
    }
    
    @Test
    public void testMakeGuessPartial() throws Exception{
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(false);
        Game gameD = gameDao.addGame(game);
        game.setGameId(gameDao.getAllGames().get(1).getGameId());
        
        Round guess = service.makeGuess(gameD, "4321");
        List<Round> rounds = service.rounds(game);
        assertTrue(guess.getExact() == 0);
        assertTrue(guess.getPartial() == 4);
        assertEquals(guess, rounds.get(0));
    }

    @Test
    public void testGetAllRounds() {
        Game game = new Game();
        game.setGameId(1);
        game.setStatus(false);
        
        Round round = new Round();
        round.setRoundId(1);
        round.setTime(service.rounds(game).get(0).getTime());
        round.setRoundNumber(1);
        round.setGuess("1234");
        round.setPartial(0);
        round.setExact(0);
        round.setGame(1);
        
        List<Round> rounds = service.rounds(game);
        assertEquals(rounds.size(), 1);
        assertEquals(round, rounds.get(0));
    }
    
    @Test
    public void testMakeCorrectGuess() throws Exception{
        Game game = new Game();
        game.setGameId(2);
        game.setStatus(true);
        game.setAnswer("4567");
        try {
            Round guess = service.makeGuess(game, "4567");
            fail();
        } catch(Exception e) {
            
        }
        
        try {
            Round guess = service.makeGuess(game, "45*7");
            fail();
        } catch(Exception e) {
            
        }
    }
    
    @Test
    public void testMakeWrongGuess() throws Exception{
        Game game = new Game();
        game.setGameId(2);
        game.setStatus(true);
        game.setAnswer("4567");
        try {
            Round guess = service.makeGuess(game, "4444");
            fail();
        } catch(Exception e) {
            
        } 
        
        try {
            Round guess = service.makeGuess(game, "1231");
            fail();
        } catch(Exception e) {
            
        }
    }
}
