/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Items;
import com.sg.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Jose
 */
public class VendingMachineController {

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }
    private VendingMachineServiceLayer service;
    private VendingMachineView view;

    public void run() {
        boolean runAgain = true;
        //int itemSelection = 0;

        while (runAgain) {
            try {
                getItems();
                funds();
                //add();
                //delete();
                runAgain = exit();
               // choice();

            } catch (VendingMachinePersistenceException 
                    | VendingMachineInsufficientFundsException | VendingMachineNoItemInventoryException e) {
                view.printError(e.getMessage());
            } catch (NumberFormatException e) {
                view.printError("Must enter in numbers!");
            }
        }
    }

    private void getItems() throws VendingMachinePersistenceException {
        List<Items> itemList = service.getAllItems();
        view.getItemSelection(itemList);
    }
    
    private BigDecimal funds() throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException, NumberFormatException {
        List<Items> itemList = service.getAllItems();
        String money = view.getMoney();
        Items choice = view.vendChoice(itemList);
        String itemCost = choice.getItemCost();
        String inventoryChange = choice.getItemName();
        BigDecimal bigMoney = new BigDecimal(money);
        BigDecimal difference = service.fundsCalculator(itemCost, bigMoney);
        if (difference.compareTo(new BigDecimal(0)) < 0) {
            BigDecimal change = service.fundsCalculator(itemCost, bigMoney);
            Change myChange = service.change(change);
            
            view.correctChange(myChange);
            service.hasInventory(inventoryChange);
            view.thankYou();
        } else if (service.hasInventory(inventoryChange) > 0){
            service.hasInventory(inventoryChange);
        } else if(difference.compareTo(new BigDecimal(0)) == 0) {
            view.thankYou();
        }else{
            service.hasEnoughFunds(itemCost, bigMoney);
        }
        return difference;
    }    

//    private void add() throws VendingMachinePersistenceException {
//        Items item = view.getItemInfo();
//        service.addItem(item.getItemName(), item);
//    }
//    
//    private void delete() throws VendingMachinePersistenceException {
//        String itemName = view.getRemovedChoice();
//        Items item = service.removeItem(itemName);
//        view.displayItemRemovedBanner(item);
//    }
    
    private boolean exit() throws VendingMachinePersistenceException {
        return view.getExit();
    }
}
