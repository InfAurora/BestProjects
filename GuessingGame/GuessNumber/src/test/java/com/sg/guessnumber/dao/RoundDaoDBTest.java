/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessnumber.dao;

import com.sg.guessnumber.entity.Game;
import com.sg.guessnumber.entity.Round;
import java.sql.Timestamp;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.* ;
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
public class RoundDaoDBTest {
    
    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    RoundDao roundDao;
    
    @Autowired
    GameDao gameDao;
    
    public RoundDaoDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        jdbc.update("DELETE FROM Round WHERE gameId != 1 Or roundId != 1");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddRound() {
        Game game = gameDao.getGame(1);
        Round round = new Round();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        time.setNanos(0);
        round.setTime(time);
        round.setRoundNumber(1);
        round.setGuess("1234");
        round.setPartial(0);
        round.setExact(0);
        round.setGame(game.getGameId());
        
        round = roundDao.addRound(round);
        List<Round> testRound = roundDao.getAllRounds(game);
       
        assertEquals(testRound.get(1),round);
    }
    
    @Test
    public void testGetAllRounds() {
        Game game = new Game();
        game.setGameId(1);
        List<Round> rounds = roundDao.getAllRounds(game);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        time = rounds.get(0).getTime();
        time.setNanos(0);
        Round round = new Round();
        round.setRoundId(1);
        round.setTime(time);
        round.setRoundNumber(1);
        round.setGuess("1234");
        round.setPartial(0);
        round.setExact(0);
        round.setGame(1);
        
        assertEquals(rounds.size(), 1);
        assertTrue(rounds.contains(round));
    }
    
}
