package com.sg.guessnumber.dao;

import com.sg.guessnumber.entity.Game;
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
public interface GameDao {
    Game addGame (Game game);
    List<Game> getAllGames();
    Game getGame(int gameId);
    void updateGameStatus(Game game);
}
