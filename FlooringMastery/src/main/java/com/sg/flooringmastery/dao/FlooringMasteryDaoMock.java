/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author InfAurora
 */
public class FlooringMasteryDaoMock implements FlooringMasteryDao{
    private final Map<Integer, Order> orders = new HashMap<>();

    @Override
    public Order addOrder(int orderNumber, Order order, LocalDate date) throws FlooringMasteryPersistenceException {
        order = orders.put(orderNumber, order);
        return order;
    }

    @Override
    public Order removeOrder(int orderNumber, LocalDate date) throws FlooringMasteryPersistenceException {
        Order order = orders.remove(orderNumber);
        return order;
    }

    @Override
    public Order editOrder(int orderNumber, LocalDate date, Order order) throws FlooringMasteryPersistenceException {
        order = orders.replace(orderNumber, order);
        return order;
    }

    @Override
    public List<Order> listOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        return new ArrayList(orders.values());
    }

    @Override
    public void exportAll() throws FlooringMasteryPersistenceException {

    }

    @Override
    public Order getOneOrder(int orderNumber, LocalDate date) throws FlooringMasteryPersistenceException {
        return orders.get(orderNumber);
    }
    
}
