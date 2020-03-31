package com.sg.guessnumber.service;

import com.sg.guessnumber.dao.GameDao;
import com.sg.guessnumber.dao.RoundDao;
import com.sg.guessnumber.dao.guessNumberPersistenceException;
import com.sg.guessnumber.entity.Game;
import com.sg.guessnumber.entity.Round;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jose
 */
@Service
public class GuessNumberService {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public Game addGame() {
        Random rng = new Random();
        Game game = new Game();
        String answer = "";

        while (answer.length() < 4) {
            int randomInt = rng.nextInt(10);
            if (!answer.contains(Integer.toString(randomInt))) {
                answer += randomInt;
            }
        }
        game.setAnswer(answer);
        game.setStatus(false);
        game = gameDao.addGame(game);
        game.setAnswer("****");
        return game;
    }

    public Game getGame(int gameId) {
        Game game = gameDao.getGame(gameId);
        game.setAnswer(gameDao.getGame(gameId).getAnswer());
        if (game.getStatus() == false) {
            game.setAnswer("****");
        }
        return game;
    }

    public List<Game> getGames() {
        List<Game> game = gameDao.getAllGames();
        for (Game g : game) {
            if (g.getStatus() == false) {
                g.setAnswer("****");
            }
        }
        return game;
    }

    public Round makeGuess(Game game, String guess) throws guessNumberPersistenceException {
        if (Pattern.matches("[0-9]{4}", guess)) {
            Round round = new Round();
            for (int i = 0; i < guess.length(); i++) {
                for (int j = 0; j < guess.length(); j++) {
                    if (i == j) {

                    } else {
                        if (guess.charAt(i) == guess.charAt(j)) {
                            throw new guessNumberPersistenceException("Must not contain "
                                    + "duplicates!");
                        } else {
                            continue;
                        }
                    }
                }
            }
            while (game.getStatus() == false) {
                game.setAnswer(gameDao.getGame(game.getGameId()).getAnswer());
                round.setGuess(guess);
                Timestamp time = new Timestamp(System.currentTimeMillis());
                time.setNanos(0);
                round.setGame(game.getGameId());
                int exact = 0;

                round.setTime(time);

                if (roundDao.getAllRounds(game).size() == 0) {
                    round.setRoundNumber(1);
                } else {
                    round.setRoundNumber(roundDao.getAllRounds(game).size() + 1);
                }

                //round.setGuess(round.getGuess()); I set guess in controller
                if (game.getAnswer().equals(round.getGuess())) {
                    round.setPartial(0);

                } else {
                    int partial = 0;
                    for (int i = 0; i < game.getAnswer().length(); i++) {
                        char wow = game.getAnswer().charAt(i);
                        if (round.getGuess().contains(Character.toString(wow))) {
                            partial++;
                        }
                        if (round.getGuess().charAt(i) == game.getAnswer().charAt(i)) {
                            partial--;
                        }

                    }
                    round.setPartial(partial);
                }

                if (game.getAnswer().equals(round.getGuess())) {
                    exact += 4;

                } else {
                    for (int i = 0; i < game.getAnswer().length(); i++) {
                        if (game.getAnswer().charAt(i) == round.getGuess().charAt(i)) {
                            exact++;
                        }
                    }
                }
                round.setExact(exact);
                round.setGame(game.getGameId());
                if (round.getExact() == 4) {
                    game.setStatus(true);
                    game.setAnswer(gameDao.getGame(game.getGameId()).getAnswer());
                    gameDao.updateGameStatus(game);
                }
                return roundDao.addRound(round);
            }
            //exception when finished
            throw new guessNumberPersistenceException("This game is already finished");
        } else {
            throw new guessNumberPersistenceException("Guess must only contain 4 numbers.");
        }
    }

    public List<Round> rounds(Game game) {

        return roundDao.getAllRounds(game);
    }
}
