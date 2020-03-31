/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jose
 */
public class FlooringMasteryDaoTaxesFileImplTest {
    
    FlooringMasteryDaoTaxesFileImpl dao = new FlooringMasteryDaoTaxesFileImpl();
    
    public FlooringMasteryDaoTaxesFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() throws Exception{
        dao.getTaxes();
        
        assertTrue(dao.getTaxes().size() == 4);
        assertTrue(dao.getTaxes().get(0).getStateName().equalsIgnoreCase("Texas"));
        assertTrue(dao.getTaxes().get(1).getStateName().equalsIgnoreCase("Kentucky"));
        assertTrue(dao.getTaxes().get(2).getStateName().equalsIgnoreCase("California"));
        assertTrue(dao.getTaxes().get(3).getStateName().equalsIgnoreCase("Washington"));
    }
    
}
