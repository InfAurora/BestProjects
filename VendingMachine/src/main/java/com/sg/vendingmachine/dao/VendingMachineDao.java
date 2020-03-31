/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Items;
import com.sg.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineNoItemInventoryException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Jose
 */
public interface VendingMachineDao {

//    Items addItem(String itemName, Items itemCost) throws VendingMachinePersistenceException;

//    Items removeItem(String itemName) throws VendingMachinePersistenceException;

    List<Items> getAllItems() throws VendingMachinePersistenceException;

//    Items cost(String itemName) throws VendingMachinePersistenceException;
    
    String inventoryChange(String itemName) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException;
    
}
