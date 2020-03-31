package com.sg.guessnumber.dao;

import com.sg.guessnumber.entity.Game;
import com.sg.guessnumber.entity.Round;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jose
 */
public interface RoundDao {
    Round addRound(Round round);
    List<Round> getAllRounds(Game gameId);
}
