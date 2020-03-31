/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

/**
 *
 * @author Jose
 */
public class Change {
    
    private int pennies;
    private int nickles;
    private int dimes;
    private int quarters;
    private int dollars;
    
    public Change(int pennies, int nickles, int dimes, int quarters, int dollars) {
        this.pennies = pennies;
        this.nickles = nickles;
        this.dimes = dimes;
        this.quarters = quarters;
        this.dollars = dollars;
    }

    /**
     * @return the pennies
     */
    public int getPennies() {
        return pennies;
    }

    /**
     * @return the nickles
     */
    public int getNickles() {
        return nickles;
    }

    /**
     * @return the dimes
     */
    public int getDimes() {
        return dimes;
    }

    /**
     * @return the quarters
     */
    public int getQuarters() {
        return quarters;
    }

    /**
     * @return the dollars
     */
    public int getDollars() {
        return dollars;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.pennies;
        hash = 53 * hash + this.nickles;
        hash = 53 * hash + this.dimes;
        hash = 53 * hash + this.quarters;
        hash = 53 * hash + this.dollars;
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
        final Change other = (Change) obj;
        if (this.pennies != other.pennies) {
            return false;
        }
        if (this.nickles != other.nickles) {
            return false;
        }
        if (this.dimes != other.dimes) {
            return false;
        }
        if (this.quarters != other.quarters) {
            return false;
        }
        if (this.dollars != other.dollars) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Change{" + "pennies=" + pennies + ", nickles=" + nickles + ", dimes=" + dimes + ", quarters=" + quarters + ", dollars=" + dollars + '}';
    }
    
    
}
