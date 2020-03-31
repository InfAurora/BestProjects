/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoMock;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoMock;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Items;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Jose
 */
public class VendingMachineServiceLayerTest {

    VendingMachineDao dao = new VendingMachineDaoMock();
    VendingMachineAuditDao aud = new VendingMachineAuditDaoMock();
    VendingMachineServiceLayer service = new VendingMachineServiceLayer(dao, aud);

    public VendingMachineServiceLayerTest() {
    }

    @Test
    public void testFundsCalculator() throws Exception {
        String itemPrice1 = "1.50";
        BigDecimal funds1 = new BigDecimal("1.45");
        String itemPrice2 = "1.75";
        BigDecimal funds2 = new BigDecimal("1.80");
        String itemPrice3 = "10";
        BigDecimal funds3 = new BigDecimal(8);
        String itemPrice4 = "-1.25";
        BigDecimal funds4 = new BigDecimal(1.25);

        BigDecimal total1 = service.fundsCalculator(itemPrice1, funds1);
        BigDecimal total2 = service.fundsCalculator(itemPrice2, funds2);
        BigDecimal total3 = service.fundsCalculator(itemPrice3, funds3);
        BigDecimal total4 = service.fundsCalculator(itemPrice4, funds4);

        assertEquals(total1, new BigDecimal("0.05"));
        assertEquals(total2, new BigDecimal("-0.05"));
        assertEquals(total3, new BigDecimal(2));
        assertEquals(total4, new BigDecimal("-2.50"));
    }

    @Test
    public void fundsExceptionTest1() throws Exception {
        String itemPrice1 = "1.50";
        BigDecimal funds1 = new BigDecimal("1.45");
        try {
            service.hasEnoughFunds(itemPrice1, funds1);
            fail();
        } catch (VendingMachineInsufficientFundsException e) {

        }
    }

    @Test
    public void fundsNumberFormatTest2() throws Exception {
        String itemPrice1 = "a";
        BigDecimal funds1 = new BigDecimal("1.03");
        try {
            service.hasEnoughFunds(itemPrice1, funds1);
            fail();
        } catch (NumberFormatException e) {

        }
    }

    @Test
    public void changeTest() throws Exception{
        Change change = service.change(new BigDecimal("-2.25"));
        assertEquals(change, new Change(0,0,0,1,2));
        assertEquals(change.getDollars(), 2);
    }
    
    @Test
    public void inventoryCheckTest() throws Exception {
        Items item = new Items("Doritos", "1.00", "0");
        
        try {
            service.hasInventory("Doritos");
            fail();
        } catch(VendingMachineNoItemInventoryException e) {
            
        }
        
    }
}
