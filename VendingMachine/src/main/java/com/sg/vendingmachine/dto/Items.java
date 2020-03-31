/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.util.Objects;

/**
 *
 * @author Jose
 */
public class Items {

    private String itemName;
    private String itemCost;
    private String itemInventory;
    
    public Items(String itemName, String itemCost, String itemInventory) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.itemInventory = itemInventory;
    }
    public Items() {
    }


    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the itemCost
     */
    public String getItemCost() {
        return itemCost;
    }

    /**
     * @param itemCost the itemCost to set
     */
    public void setItemCost(String itemCost) {
        this.itemCost = itemCost;
    }

    /**
     * @return the itemInventory
     */
    public String getItemInventory() {
        return itemInventory;
    }

    /**
     * @param itemInventory the itemInventory to set
     */
    public void setItemInventory(String itemInventory) {
        this.itemInventory = itemInventory;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.itemName);
        hash = 71 * hash + Objects.hashCode(this.itemCost);
        hash = 71 * hash + Objects.hashCode(this.itemInventory);
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
        final Items other = (Items) obj;
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemCost, other.itemCost)) {
            return false;
        }
        if (!Objects.equals(this.itemInventory, other.itemInventory)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Items{" + "itemName=" + itemName + ", itemCost=" + itemCost + ", itemInventory=" + itemInventory + '}';
    }
    
}
