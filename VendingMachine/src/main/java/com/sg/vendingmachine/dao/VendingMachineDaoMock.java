/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Items;
import com.sg.vendingmachine.service.VendingMachineNoItemInventoryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Jose
 */
public class VendingMachineDaoMock implements VendingMachineDao{
    private Map<String, Items> item = new HashMap<>();

    @Override
    public List<Items> getAllItems() throws VendingMachinePersistenceException {
        return new ArrayList(item.values());
    }

    @Override
    public String inventoryChange(String itemName) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        if (itemName == "Doritos") {
            return "0";
        }
        return "0";
    }
    
}
