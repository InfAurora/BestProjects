/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Items;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 */
public class VendingMachineView {

    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    private UserIO io;

    public void getItemSelection(List<Items> itemList) {
        int i = 0;
        for (Items item : itemList) {
//            if (item.getItemInventory().compareTo("0") != 0) {
//                i++;
//                io.print(i + ".) " + item.getItemName() + " $" + item.getItemCost());
//            }
            i++;
            io.print(i + ".) " + item.getItemName() + " $" + item.getItemCost());
        }
    }

//    public Items getItemInfo() {
//
//        String itemName = io.readString("Please enter item name.");
//        String itemCost = io.readString("Please enter cost of item.");
//        String amount = io.readString("Please enter the amount of items entering.");
//        Items newItem = new Items();
//        newItem.setItemName(itemName);
//        newItem.setItemCost(itemCost);
//        newItem.setItemInventory(amount);
//
//        return newItem;
//    }

    public String getMoney() {
        return io.readString("Please enter in the correct amount of money.");

    }

    public void printError(String message) {
        io.print("Error: " + message);
    }

    public Items vendChoice(List<Items> itemList) {
        int choice = io.readInt("Please select your item.", 1, itemList.size());
        return itemList.get(choice - 1);
    }

    public String getRemovedChoice() {
        return io.readString("What item would you like to remove?");
    }

    public void displayItemRemovedBanner(Items item) {
        if (item != null) {
            io.print("Item was successfully removed.");
        } else {
            io.print("That item does not exist!");
        }
    }

    public boolean getExit() {
        String question = io.readString("Would you like to exit? Yes, or no");
        if (question.equals("yes")) {
            return false;
        } else if (question.equals("YES")) {
            return false;
        } else if (question.equals("Yes")) {
            return false;
        }
        return true;
    }
    
    public void correctChange(Change change) {
        io.print("Here is your change. ");
        io.print("Dollars: " + change.getDollars());
        io.print("Quarters: " + change.getQuarters());
        io.print("Dimes: " + change.getDimes());
        io.print("Nickles: " + change.getNickles());
        io.print("Pennies: " + change.getPennies());
    }

    public void thankYou() {
        io.print("Thank You!");
    }
}
