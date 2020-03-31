/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Products;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Jose
 */
public class FlooringMasteryDaoProductsFileImplTest {
    FlooringMasteryDaoProductsFileImpl dao = new FlooringMasteryDaoProductsFileImpl();
    
    public FlooringMasteryDaoProductsFileImplTest() {
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
        dao.getProducts();
        
        assertTrue(dao.getProducts().size() == 4);
        assertTrue(dao.getProducts().get(0).getProductType().equalsIgnoreCase("Laminate"));
        assertTrue(dao.getProducts().get(1).getProductType().equalsIgnoreCase("Wood"));
        assertTrue(dao.getProducts().get(2).getProductType().equalsIgnoreCase("Tile"));
        assertTrue(dao.getProducts().get(3).getProductType().equalsIgnoreCase("Carpet"));
        
    }
    
}
