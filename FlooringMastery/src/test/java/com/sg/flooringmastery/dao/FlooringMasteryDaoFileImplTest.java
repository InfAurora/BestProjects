/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Jose
 */
public class FlooringMasteryDaoFileImplTest {

    FlooringMasteryDaoFileImpl dao = new FlooringMasteryDaoFileImpl();

    public FlooringMasteryDaoFileImplTest() {

    }

//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
    @BeforeEach
    public void setUp() throws Exception {
        String date = "02282000";
        LocalDate datePick = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order = new Order(1, "Jose", "Texas", new BigDecimal("4.45"),
                "Tile", new BigDecimal(300), new BigDecimal("3.50"), new BigDecimal("4.15"),
                new BigDecimal("1050.00"), new BigDecimal("1245.00"),
                new BigDecimal("102.127500"), new BigDecimal("2397.127500"));

        Order order2 = new Order(2, "Lucy", "California", new BigDecimal("25.00"),
                "Tile", new BigDecimal(300), new BigDecimal("3.50"), new BigDecimal("4.15"),
                new BigDecimal("1050.00"), new BigDecimal("1245.00"),
                new BigDecimal("102.127500"), new BigDecimal("2868.7500"));

        Order order3 = new Order(3, "Killa", "Texas", new BigDecimal("4.45"),
                "Tile", new BigDecimal(300), new BigDecimal("3.50"), new BigDecimal("4.15"),
                new BigDecimal("1050.00"), new BigDecimal("1245.00"),
                new BigDecimal("102.127500"), new BigDecimal("2397.127500"));

        Order order4 = new Order(4, "QQQ", "Washington", new BigDecimal("9.25"),
                "Laminate", new BigDecimal(10), new BigDecimal("1.75"), new BigDecimal("2.10"),
                new BigDecimal("17.50"), new BigDecimal("21.00"),
                new BigDecimal("3.561250"), new BigDecimal("42.061250"));
        dao.addOrder(1, order, datePick);
        dao.addOrder(2, order2, datePick);
        dao.addOrder(3, order3, datePick);
        dao.addOrder(4, order4, datePick);

    }

    @AfterEach
    public void tearDown() throws Exception {
        String date = "02282000";
        LocalDate datePick = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy"));
        dao.removeOrder(5, datePick);
    }

    @Test
    public void testlistOrders() throws Exception {
        String date = "02282000";
        LocalDate datePick = LocalDate.now();
        datePick = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order4 = new Order(4, "QQQ", "Washington", new BigDecimal("9.25"),
                "Laminate", new BigDecimal(10), new BigDecimal("1.75"), new BigDecimal("2.10"),
                new BigDecimal("17.50"), new BigDecimal("21.00"),
                new BigDecimal("3.561250"), new BigDecimal("42.061250"));
        
        Order order5 = new Order(5, "Q,Q,Q", "Washington", new BigDecimal("9.25"),
                "Laminate", new BigDecimal(10), new BigDecimal("1.75"), new BigDecimal("2.10"),
                new BigDecimal("17.50"), new BigDecimal("21.00"),
                new BigDecimal("3.561250"), new BigDecimal("42.061250"));
        
        dao.addOrder(5, order5, datePick);

        assertEquals(dao.listOrders(datePick).get(4),order5);
        assertTrue(dao.listOrders(datePick).size() == 5);
        assertTrue(dao.listOrders(datePick).contains(order4));
        assertFalse(dao.listOrders(datePick).isEmpty());

    }

    @Test
    public void testGetOneOrder() throws Exception {
        String date = "02282000";
        LocalDate datePick = LocalDate.now();
        datePick = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order4 = new Order(4, "QQQ", "Washington", new BigDecimal("9.25"),
                "Laminate", new BigDecimal(10), new BigDecimal("1.75"), new BigDecimal("2.10"),
                new BigDecimal("17.50"), new BigDecimal("21.00"),
                new BigDecimal("3.561250"), new BigDecimal("42.061250"));

        Order order3 = new Order(3, "Killa", "Texas", new BigDecimal("4.45"),
                "Tile", new BigDecimal(300), new BigDecimal("3.50"), new BigDecimal("4.15"),
                new BigDecimal("1050.00"), new BigDecimal("1245.00"),
                new BigDecimal("102.127500"), new BigDecimal("2397.127500"));
        
        Order order5 = new Order(5, "Q,Q,Q", "Washington", new BigDecimal("9.25"),
                "Laminate", new BigDecimal(10), new BigDecimal("1.75"), new BigDecimal("2.10"),
                new BigDecimal("17.50"), new BigDecimal("21.00"),
                new BigDecimal("3.561250"), new BigDecimal("42.061250"));
        
        dao.addOrder(5, order5, datePick);
        
        assertEquals(dao.getOneOrder(5, datePick), order5);
        assertTrue(dao.getOneOrder(4, datePick).equals(order4));
        assertFalse(dao.getOneOrder(4, datePick).equals(order3));
        
        
        //test ,
    }

    @Test
    public void testAdd() throws Exception {
        String date = "02282000";
        LocalDate datePick = LocalDate.now();
        datePick = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order = new Order(5, "Carrie", "Texas", new BigDecimal("4.45"),
                "Tile", new BigDecimal(300), new BigDecimal("3.50"), new BigDecimal("4.15"),
                new BigDecimal("1050.00"), new BigDecimal("1245.00"),
                new BigDecimal("102.127500"), new BigDecimal("2397.127500"));

        dao.addOrder(5, order, datePick);

        assertTrue(dao.listOrders(datePick).size() == 5);
        assertTrue(dao.listOrders(datePick).contains(order));
    }

    @Test
    public void testRemove() throws Exception {
        String date = "02282000";
        LocalDate datePick = LocalDate.now();
        datePick = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy"));
        dao.removeOrder(3, datePick);
        Order order2 = new Order(2, "Lucy", "California", new BigDecimal("25.00"),
                "Tile", new BigDecimal(300), new BigDecimal("3.50"), new BigDecimal("4.15"),
                new BigDecimal("1050.00"), new BigDecimal("1245.00"),
                new BigDecimal("102.127500"), new BigDecimal("2868.7500"));

        Order order3 = new Order(3, "Killa", "Texas", new BigDecimal("4.45"),
                "Tile", new BigDecimal(300), new BigDecimal("3.50"), new BigDecimal("4.15"),
                new BigDecimal("1050.00"), new BigDecimal("1245.00"),
                new BigDecimal("102.127500"), new BigDecimal("2397.127500"));

        Order order4 = new Order(4, "QQQ", "Washington", new BigDecimal("9.25"),
                "Laminate", new BigDecimal(10), new BigDecimal("1.75"), new BigDecimal("2.10"),
                new BigDecimal("17.50"), new BigDecimal("21.00"),
                new BigDecimal("3.561250"), new BigDecimal("42.061250"));

        assertTrue(dao.listOrders(datePick).size() == 3);
        assertFalse(dao.listOrders(datePick).contains(order3));
        assertTrue(dao.listOrders(datePick).contains(order2));
        assertTrue(dao.listOrders(datePick).contains(order4));
    }
    
    @Test
    public void testEditOrder() throws Exception {
        String date = "02282000";
        LocalDate datePick = LocalDate.now();
        datePick = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy"));
        Order order5 = new Order(5, "Killa", "Texas", new BigDecimal("4.45"),
                "Tile", new BigDecimal(300), new BigDecimal("3.50"), new BigDecimal("4.15"),
                new BigDecimal("1050.00"), new BigDecimal("1245.00"),
                new BigDecimal("102.127500"), new BigDecimal("2397.127500"));

        Order editedOrder = new Order(5, "CutiePie", "Washington", new BigDecimal("9.25"),
                "Laminate", new BigDecimal(10), new BigDecimal("1.75"), new BigDecimal("2.10"),
                new BigDecimal("17.50"), new BigDecimal("21.00"),
                new BigDecimal("3.561250"), new BigDecimal("42.061250"));
        
        dao.addOrder(5, order5, datePick);
        dao.editOrder(5, datePick, editedOrder);
        Order getOrder = dao.getOneOrder(5, datePick);
        assertTrue(getOrder.equals(editedOrder));
        assertFalse(getOrder.equals(order5));
    }

}
