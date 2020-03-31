/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sg.flooringmastery.dao.FlooringMasteryDaoProducts;
import com.sg.flooringmastery.dao.FlooringMasteryDaoTaxes;
import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Products;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose
 */
@Component
public class FlooringMasteryServiceLayer {

    private FlooringMasteryDao dao;
    private FlooringMasteryDaoProducts dao2;
    private FlooringMasteryDaoTaxes dao3;

    @Autowired
    public FlooringMasteryServiceLayer(FlooringMasteryDao dao, FlooringMasteryDaoProducts dao2, FlooringMasteryDaoTaxes dao3) {
        this.dao = dao;
        this.dao2 = dao2;
        this.dao3 = dao3;
    }

    public List<Order> listOrders(LocalDate date) throws FlooringMasteryPersistenceException {

        return dao.listOrders(date);
    }

    public Order addOrder(Order order, String state, LocalDate date) throws FlooringMasteryPersistenceException {
        List<Order> orderList = new ArrayList<>();
        try {
            orderList = dao.listOrders(date);
        } catch(FlooringMasteryPersistenceException e) {
            //makes file if it does not exist ye
        }
        
        LocalDate todaysDate = LocalDate.now();
        String formattedDate = todaysDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        if (orderList.size() == 0) {
            order.setOrderNumber(1);
            order.setTaxRate(getTax(state));
            order.setMaterialCost(getMaterialCost(order));
            order.setLaborCost(getLaborCost(order));
            order.setTax(getTaxTotal(order));
            order.setTotal(getTotal(order));
            
            return dao.addOrder(1, order, date);
        } else {
            int orderNum = orderList.get(orderList.size() - 1).getOrderNumber();
            order.setOrderNumber(orderNum + 1);
        }
        order.setMaterialCost(getMaterialCost(order));
        order.setLaborCost(getLaborCost(order));
        order.setTaxRate(getTax(state));
        order.setTax(getTaxTotal(order));
        order.setTotal(getTotal(order));
        
        return dao.addOrder(order.getOrderNumber(), order, date);
    }

    public List<Products> getProducts() throws FlooringMasteryPersistenceException {

        return dao2.getProducts();
    }
    
    public List<Tax> getStates() throws FlooringMasteryPersistenceException {

        return dao3.getTaxes();
    }

//    public int getOrderNum(LocalDate date) throws FlooringMasteryPersistenceException {
//        Order order = new Order();
//        List<Order> orderList = dao.listOrders(date);
//        int indexValue = orderList.get(orderList.size() - 1).getOrderNumber();
//        order.setOrderNumber(indexValue + 1);
//        return indexValue + 1;
//    }
    
    public BigDecimal getTax(String state) throws FlooringMasteryPersistenceException{
        List<Tax> taxList = dao3.getTaxes();
        for (int i = 0; i < taxList.size(); i++) {
            String stateName = dao3.getTaxes().get(i).getStateName();
            BigDecimal taxNumber = dao3.getTaxes().get(i).getTaxRate();
            if (stateName.equalsIgnoreCase(state)) {
                return taxNumber;
            }
        }
        return new BigDecimal(-1);
    }
    
    public BigDecimal getMaterialCost(Order order) throws FlooringMasteryPersistenceException{
        List<Products> products = dao2.getProducts();
        for (int i = 0; i < products.size(); i++) {
            String material = products.get(i).getProductType();
            if (material.equals(order.getProductType())) {
                BigDecimal materialCost = products.get(i).getCostPerSquareFoot()
                .multiply(order.getArea());
                return materialCost;
            }
        }
        
        return null;
    }
    
    public BigDecimal getLaborCost(Order order) throws FlooringMasteryPersistenceException{
        List<Products> products = dao2.getProducts();
        for (int i = 0; i < products.size(); i++) {
            String material = products.get(i).getProductType();
            if (material.equals(order.getProductType())) {
                BigDecimal laborCost = products.get(i).getLaborCostPerSquareFoot()
                .multiply(order.getArea());
                return laborCost;
            }
        }
        
        return null;
    }
    
    public BigDecimal getTaxTotal(Order order) throws FlooringMasteryPersistenceException{
        BigDecimal taxTotal = (getMaterialCost(order).add(getLaborCost(order)))
                .multiply(order.getTaxRate().divide(new BigDecimal(100)));
        return taxTotal;
    }
    
    public BigDecimal getTotal(Order order) throws FlooringMasteryPersistenceException{
        BigDecimal total = getMaterialCost(order).add(getLaborCost(order).add(getTaxTotal(order)));
        return total;
    }
    
    public Order remove(int orderNumber, LocalDate date) throws FlooringMasteryPersistenceException{
        
        
        return dao.removeOrder(orderNumber, date);
    }
    
    public Order edit(int orderNumber, LocalDate date, Order order, String state) throws FlooringMasteryPersistenceException{
        editOrder(order, state, date, orderNumber);
        return dao.editOrder(orderNumber, date, order);
    }
    
    public Order editOrder(Order order, String state, LocalDate date, int orderNumber) throws FlooringMasteryPersistenceException {
//        List<Order> orderList = new ArrayList<>();
//        try {
//            orderList = dao.listOrders(date);
//        } catch(FlooringMasteryPersistenceException e) {
//            //makes file if it does not exist ye
//        }
        
        order.setOrderNumber(orderNumber);
        order.setMaterialCost(getMaterialCost(order));
        order.setLaborCost(getLaborCost(order));
        order.setTaxRate(getTax(state));
        order.setTax(getTaxTotal(order));
        order.setTotal(getTotal(order));
        
        return order;
    }
    
    public void exportAll() throws FlooringMasteryPersistenceException {
        
        dao.exportAll();
    }
    
    public Order getOneOrder(int orderNumber, LocalDate date) throws FlooringMasteryPersistenceException {
                
        return dao.getOneOrder(orderNumber, date);

    }
    
}
    