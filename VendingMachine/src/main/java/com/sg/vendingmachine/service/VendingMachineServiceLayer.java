/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Items;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Jose
 */
public class VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayer(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
//    public Items addItem(String itemName, Items item) throws VendingMachinePersistenceException {
//        auditDao.writeAuditEntry("Item " + item.getItemName() + " CREATED");
//        return dao.addItem(itemName, item);
//    }

    public BigDecimal fundsCalculator(String itemPrice, BigDecimal funds) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException {
        //String itemPrice = item.get(itemName).getItemCost();
        BigDecimal convertedItemPrice = new BigDecimal(itemPrice);
        return convertedItemPrice.subtract(funds);
    }

    public Change change(BigDecimal remainder) throws VendingMachinePersistenceException {
        Change change;
        
        BigDecimal penny = new BigDecimal("0.01");
        int pennyCounter = 0;

        BigDecimal nickle = new BigDecimal("0.05");
        int nickleCounter = 0;

        BigDecimal dime = new BigDecimal("0.10");
        int dimeCounter = 0;

        BigDecimal quarter = new BigDecimal("0.25");
        int quarterCounter = 0;

        BigDecimal dollar = new BigDecimal(1);
        int dollarCounter = 0;

        while (remainder.compareTo(new BigDecimal(0)) < 0) {
            if (remainder.remainder(dollar).compareTo(new BigDecimal(0)) <= 0) {
                remainder = (remainder.add(dollar));
                if (remainder.remainder(dollar).compareTo(new BigDecimal(0)) <= 0) {
                    dollarCounter++;
                }

            }
        }

        if (remainder.compareTo(new BigDecimal(0)) > 0) {
            remainder = (remainder.subtract(dollar));
            while (remainder.compareTo(new BigDecimal(0)) < 0) {
                if (remainder.remainder(quarter).compareTo(new BigDecimal(0)) <= 0) {
                    remainder = (remainder.add(quarter));
                    if (remainder.remainder(quarter).compareTo(new BigDecimal(0)) <= 0) {
                        quarterCounter++;
                    }
                }
            }
        }

        if (remainder.compareTo(new BigDecimal(0)) > 0) {
            remainder = (remainder.subtract(quarter));
            while (remainder.compareTo(new BigDecimal(0)) < 0) {
                if (remainder.remainder(dime).compareTo(new BigDecimal(0)) <= 0) {
                    remainder = (remainder.add(dime));
                    if (remainder.remainder(dime).compareTo(new BigDecimal(0)) <= 0) {
                        dimeCounter++;
                    }
                }
            }
        }

        if (remainder.compareTo(new BigDecimal(0)) > 0) {
            remainder = (remainder.subtract(dime));
            while (remainder.compareTo(new BigDecimal(0)) < 0) {
                if (remainder.remainder(nickle).compareTo(new BigDecimal(0)) <= 0) {
                    remainder = (remainder.add(nickle));
                    if (remainder.remainder(nickle).compareTo(new BigDecimal(0)) <= 0) {
                        nickleCounter++;
                    }
                }
            }
        }

        if (remainder.compareTo(new BigDecimal(0)) > 0) {
            remainder = (remainder.subtract(nickle));
            while (remainder.compareTo(new BigDecimal(0)) < 0) {
                if (remainder.remainder(penny).compareTo(new BigDecimal(0)) <= 0) {
                    remainder = (remainder.add(penny));
                    if (remainder.remainder(penny).compareTo(new BigDecimal(0)) <= 0) {
                        pennyCounter++;
                    }
                }
            }
        }
        return change = new Change(pennyCounter, nickleCounter, dimeCounter, quarterCounter, dollarCounter);
    }

    public void hasEnoughFunds(String itemPrice, BigDecimal funds) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, NumberFormatException {
        BigDecimal remainder = new BigDecimal(fundsCalculator(itemPrice, funds).compareTo(new BigDecimal(0)));
        String amountEntered = funds.toString();
        if (remainder.compareTo(new BigDecimal(0)) > 0) {
            throw new VendingMachineInsufficientFundsException("Insufficient Funds, you put in " + amountEntered);

        }

    }
    
    public int hasInventory(String itemName) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        if (dao.inventoryChange(itemName).compareTo(new BigDecimal("0").toString()) <= 0) {
            throw new VendingMachineNoItemInventoryException("There is none left in stock for that item!");
        }
        auditDao.writeAuditEntry("Item " + itemName + " was bought");
        return 0;
    }

//    public Items removeItem(String itemName) throws VendingMachinePersistenceException {
//        Items items = dao.removeItem(itemName);
//        auditDao.writeAuditEntry("Item " + itemName + " was deleted");
//        return items;
//    }
    
    public List<Items> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }
}
