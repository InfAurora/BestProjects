/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessnumber.dao;

import com.sg.guessnumber.entity.Game;
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
public class GameDaoDBTest {

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public GameDaoDBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        jdbc.update("DELETE FROM Game WHERE gameId != 1");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddAndGetGame() {
        Game game = new Game();
        //game.setGameId(1);
        game.setAnswer("1234");
        game.setStatus(false);
        gameDao.addGame(game);
        Game fromDB = gameDao.getGame(1);
        fromDB.setGameId(game.getGameId());
        assertEquals(game, fromDB);
    }

    @Test
    public void testGetAll() {
        Game game = new Game();
        //game.setGameId(1);
        game.setAnswer("5678");
        game.setStatus(false);
        gameDao.addGame(game);
        
        assertTrue(gameDao.getAllGames().size() == 2);
        assertFalse(gameDao.getAllGames().size() == 1);
        assertTrue(gameDao.getAllGames().contains(game));
    }

}
