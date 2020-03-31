/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringMasteryPersistenceException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Products;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.service.FlooringMasteryServiceLayer;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jose
 */
@Component
public class FlooringMasteryController {
    
    private FlooringMasteryServiceLayer service;
    private FlooringMasteryView view;

    @Autowired
    public FlooringMasteryController(FlooringMasteryServiceLayer service, FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean runAgain = true;
        int menuSelection = 0;

        while (runAgain) {
            try {
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        displayOrder();
                        break;
                    case 2:
                        add();
                        break;
                    case 3:
                        edit();//Edit an order
                        break;
                    case 4:
                        remove();
                        break;
                    case 5:
                        exportAll();
                        break;
                    case 6:
                        runAgain = false;
                        //exitMessage(); create exit message
                        break;
                        //add default case?
                }
            } catch (FlooringMasteryPersistenceException e) {
                view.printError(e.getMessage());
            } catch (NumberFormatException e) {
                view.printError("Must enter in a number!");
            }
        }
    }

    public int getMenuSelection() throws FlooringMasteryPersistenceException {
        return view.printMenuAndGetChoice();
    }

    public void displayOrder() throws FlooringMasteryPersistenceException {
        LocalDate date = view.getOrderChoice(); //read in int date using date class
        List<Order> orders = service.listOrders(date);
        view.displayOrdersByDate(orders);
        service.listOrders(date);
    }
    
    public void add() throws FlooringMasteryPersistenceException {
        LocalDate date = LocalDate.now();
        List<Products> productList = service.getProducts();
        List<Tax> stateList = service.getStates();
        Order order = view.getOrderInfo(productList, stateList);
        String answer = view.isSureAdd();
        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
            service.addOrder(order, order.getState(), date);
        } 
    }
    
    public Order remove() throws FlooringMasteryPersistenceException {
        boolean again = false;
        int removeChoice;
        LocalDate date;
        do {
        date = view.getOrderChoice();
        removeChoice = view.removeChoice();
        List<Order> order = service.listOrders(date);
            if (removeChoice >  order.get(order.size() - 1).getOrderNumber() || 
                    order.get(order.size() - 1) == null) {
                again = true;
                view.displayOrderDoesNotExist();
            } else {
                again = false;
            }
        } while(again == true);
        
        Order orderVisual = service.getOneOrder(removeChoice, date);
        view.displayOneOrder(orderVisual);
        String answer = view.isSureDelete();
        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
            return service.remove(removeChoice, date);
        }
        return null;
    }
    
    public Order edit() throws FlooringMasteryPersistenceException {
        boolean again = false;
        LocalDate date;
        int editChoice;
        
        do {
        date = view.getOrderChoice();
        editChoice = view.editChoice();
        List<Order> order = service.listOrders(date);
            if (editChoice >  order.get(order.size() - 1).getOrderNumber() || 
                    order.get(order.size() - 1) == null) {
                again = true;
                view.displayOrderDoesNotExist();
            } else {
                again = false;
            }
        } while(again == true);
        
        List<Products> productList = service.getProducts();
        List<Tax> stateList = service.getStates();
        Order orderVisual = service.getOneOrder(editChoice, date);
        view.displayOneOrder(orderVisual);
        
        Order order = view.getEditInfo(productList, stateList, orderVisual);
        view.displayOneOrder(order);
        //create visual in view
        String answer = view.isSureEdit();
        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
            return service.edit(editChoice, date, order, order.getState());
        }
        return null;
    }
    
    public void exportAll() throws FlooringMasteryPersistenceException {
        service.exportAll();
    }
}
