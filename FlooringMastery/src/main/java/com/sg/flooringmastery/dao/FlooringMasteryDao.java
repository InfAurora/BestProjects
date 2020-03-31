/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jose
 */
public interface FlooringMasteryDao {
    Order addOrder(int orderNumber, Order order, LocalDate date) throws FlooringMasteryPersistenceException; 
    Order removeOrder(int orderNumber, LocalDate date) throws FlooringMasteryPersistenceException;
    Order editOrder(int orderNumber, LocalDate date, Order order) throws FlooringMasteryPersistenceException;
    List<Order> listOrders(LocalDate date) throws FlooringMasteryPersistenceException;
    void exportAll() throws FlooringMasteryPersistenceException;
    Order getOneOrder(int orderNumber ,LocalDate date) throws FlooringMasteryPersistenceException;
}
