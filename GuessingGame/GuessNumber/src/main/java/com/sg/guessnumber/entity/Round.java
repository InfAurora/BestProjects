package com.sg.guessnumber.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jose
 */
public class Round {
    private int roundId;
    private Timestamp time;
    private int roundNumber;
    private String guess;
    private int partial;
    private int exact;
    private int gameId;

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public int getPartial() {
        return partial;
    }

    public void setPartial(int partial) {
        this.partial = partial;
    }

    public int getExact() {
        return exact;
    }

    public void setExact(int exact) {
        this.exact = exact;
    }

    public int getGame() {
        return gameId;
    }

    public void setGame(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.roundId;
        hash = 59 * hash + Objects.hashCode(this.time);
        hash = 59 * hash + this.roundNumber;
        hash = 59 * hash + Objects.hashCode(this.guess);
        hash = 59 * hash + this.partial;
        hash = 59 * hash + this.exact;
        hash = 59 * hash + this.gameId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.roundNumber != other.roundNumber) {
            return false;
        }
        if (this.partial != other.partial) {
            return false;
        }
        if (this.exact != other.exact) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }
    
    
}
