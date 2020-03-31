package com.sg.guessnumber.dao;

import com.sg.guessnumber.dao.GameDaoDB.GameMapper;
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
public class RoundDaoDB implements RoundDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Round addRound(Round round) {
        jdbc.update("INSERT INTO Round(`time`, roundNumber, guess, `partial`, exact, gameId) VALUES(?,?,?,?,?,?)",
                round.getTime(),
                round.getRoundNumber(),
                round.getGuess(),
                round.getPartial(),
                round.getExact(),
                round.getGame());
        //fix in dto and get id by passing in game through addRound
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(newId);

        return round;
    }

    @Override
    public List<Round> getAllRounds(Game game) {
        List<Round> rounds = jdbc.query("SELECT * FROM Round WHERE gameId = ?", new RoundMapper(),
                game.getGameId()); // 

        for (Round r : rounds) {
            r.setGame(getGameIdForRound(r).getGameId());
        }
        return rounds;
    }

    private Game getGameIdForRound(Round r) {
        try {
            return jdbc.queryForObject("SELECT g.* FROM Game g "
                    + "JOIN Round r ON g.gameId = r.gameId "
                    + "WHERE r.roundId = ?", new GameMapper(), r.getRoundId());
        } catch (DataAccessException ex) {
            return null;
        }
    }
    
    public static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int i) throws SQLException {
            Round r = new Round();
            r.setRoundId(rs.getInt("roundId"));
            r.setTime(rs.getTimestamp("time"));
            r.setRoundNumber(rs.getInt("roundNumber"));
            r.setGuess(rs.getString("guess"));
            r.setPartial(rs.getInt("partial"));
            r.setExact(rs.getInt("exact"));
            return r;
        }

    }
}
