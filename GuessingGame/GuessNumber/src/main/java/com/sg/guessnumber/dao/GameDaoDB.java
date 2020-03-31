package com.sg.guessnumber.dao;

import com.sg.guessnumber.dao.RoundDaoDB.RoundMapper;
import com.sg.guessnumber.entity.Game;
import com.sg.guessnumber.entity.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jose
 */
@Repository
public class GameDaoDB implements GameDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Game addGame(Game game) {
        jdbc.update("INSERT INTO Game(answer, `status`) VALUES(?,?)",
                game.getAnswer(),
                game.getStatus());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newId);
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = jdbc.query("SELECT * FROM Game", new GameMapper());

        for (Game g : games) {
            g.setRounds(getRoundsForGame(g));
        }
        return games;
    }

    private List<Round> getRoundsForGame(Game g) {
        return jdbc.query("SELECT r.* FROM Round r "
                + "JOIN Game g ON r.gameId = g.gameId "
                + "WHERE g.gameId = ?", new RoundMapper(), g.getGameId());
    }

    @Override
    public Game getGame(int gameId) {
        Game game = jdbc.queryForObject("SELECT * FROM Game WHERE gameId = ?", new GameMapper(), gameId);

        game.setAnswer(game.getAnswer());
        game.setStatus(game.getStatus());

        return game;
    }

    @Override
    public void updateGameStatus(Game game) {
        jdbc.update("UPDATE game SET status = True WHERE gameId = ?",
                game.getGameId());
    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int i) throws SQLException {
            Game g = new Game();
            g.setGameId(rs.getInt("gameId"));
            g.setAnswer(rs.getString("answer"));
            g.setStatus(rs.getBoolean("status"));
            return g;
        }

    }
}
