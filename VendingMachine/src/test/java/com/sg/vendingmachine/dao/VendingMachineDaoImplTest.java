/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Items;
import java.io.FileWriter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author InfAurora
 */
public class VendingMachineDaoImplTest {
    
    VendingMachineDao testDao;
    
    public VendingMachineDaoImplTest(){
        
    }
            
            
    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testItems.txt";
        FileWriter fw = new FileWriter(testFile);
        fw.append("Doritos::1.00::1");
        fw.close();
        testDao = new VendingMachineDaoImpl(testFile);
    }

    @Test
    public void testGetAllItems() throws Exception {
        Items item = new Items("Doritos", "1.00", "1");
        List<Items> list = testDao.getAllItems();
        list.add(item);
        
        assertTrue(testDao.getAllItems().size() == 1);
        assertFalse(testDao.getAllItems().isEmpty());
        assertTrue(testDao.getAllItems().contains(item));
    }
    
    @Test
    public void testInventoryChange() throws Exception {
        String change = testDao.inventoryChange("Doritos");
        assertEquals(change, "0");
        assertFalse(change == "3");
    }
}
