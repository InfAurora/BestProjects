/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Items;
import com.sg.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineNoItemInventoryException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Jose
 */
public class VendingMachineDaoImpl implements VendingMachineDao {

    private Map<String, Items> item = new HashMap<>();

    private final String ITEMS_FILE;
    final String DELIMITER = "::";

    public VendingMachineDaoImpl() {
        ITEMS_FILE = "items.txt";
    }

    public VendingMachineDaoImpl(String itemTextFile) {
        ITEMS_FILE = itemTextFile;
    }

//    @Override
//    public Items addItem(String itemName, Items items) throws VendingMachinePersistenceException {
//        try {
//            loadFile();
//        } catch (VendingMachinePersistenceException e) {
//            //the write file is now going to create a file if none exists.
//        }
//        Items newItem = item.put(itemName, items);
//        writeFile();
//        return newItem;
//    }

//    @Override
//    public Items removeItem(String itemName) throws VendingMachinePersistenceException {
//        loadFile();
//        Items items = item.remove(itemName);
//        writeFile();
//        return items;
//    }

    @Override
    public List<Items> getAllItems() throws VendingMachinePersistenceException {
        loadFile();
        return new ArrayList(item.values());
    }

//    @Override
//    public Items cost(String itemName) throws VendingMachinePersistenceException {
//        loadFile();
//        return item.get(itemName);
//    }

    private void loadFile() throws VendingMachinePersistenceException {
        Scanner newScan = null;
        try {
            newScan = new Scanner(new FileReader(ITEMS_FILE));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("File not found!");
        }

        while (newScan.hasNextLine()) {
            String line = newScan.nextLine();
            String[] parts = line.split(DELIMITER);
            Items i = new Items();
            i.setItemName(parts[0]);
            i.setItemCost(parts[1]);
            i.setItemInventory(parts[2]);

            item.put(i.getItemName(), i);
        }
        newScan.close();
    }

    private void writeFile() throws VendingMachinePersistenceException {
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new FileWriter(ITEMS_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Error writing to file");
        }

        List<Items> itemList = this.getAllItems();
        for (Items i : itemList) {
            pw.println(i.getItemName() + DELIMITER
                    + i.getItemCost() + DELIMITER
                    + i.getItemInventory());
        }
        pw.flush();
        pw.close();
    }

    @Override
    public String inventoryChange(String itemName) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        loadFile();
        Items items = item.get(itemName);

        String hi = item.get(itemName).getItemInventory();
        BigDecimal itemInventory = new BigDecimal(hi);
        if (itemInventory.compareTo(new BigDecimal(0)) == 0) {
            return itemInventory.toString();
        } else {
            String newInventory = itemInventory.subtract(new BigDecimal(1)).toString();
            items.setItemInventory(newInventory);
            writeFile();
            return newInventory;
        }
    }
}
