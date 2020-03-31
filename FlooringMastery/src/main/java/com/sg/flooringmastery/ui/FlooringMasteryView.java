/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Products;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jose
 */
@Component
public class FlooringMasteryView {

    private UserIO io;

    @Autowired
    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public void printError(String message) {
        io.print("Error: " + message);
    }

    public int printMenuAndGetChoice() {
        io.print("1.) Display Orders");
        io.print("2.) Add an Order");
        io.print("3.) Edit an Order");
        io.print("4.) Remove an Order");
        io.print("5.) Export All Data");
        io.print("6.) Quit");

        return io.readInt("Please select one of the options above.", 1, 6);
    }

    public void displayOrdersByDate(List<Order> orderList) {
        for (Order currentOrder : orderList) {
            String orderInfo = String.format("%s : %s : %s : %s : %s : %s : %s : %s : %s : %s : %s : %s",
                    currentOrder.getOrderNumber(), currentOrder.getCustomerName(), currentOrder.getState(),
                    currentOrder.getTaxRate(), currentOrder.getProductType(), currentOrder.getArea(),
                    currentOrder.getCostPerSquareFoot(), currentOrder.getLaborCostPerSquareFoot(),
                    currentOrder.getMaterialCost(), currentOrder.getLaborCost(),
                    currentOrder.getTax(), currentOrder.getTotal());
            io.print(orderInfo);
        }
    }

    public void displayOneOrder(Order order) {
        String orderInfo = String.format("%s : %s : %s : %s", order.getCustomerName(),
                order.getState(), order.getProductType(), order.getArea());
        io.print(orderInfo);
    }

    public LocalDate getOrderChoice() {
        Boolean again = false;
        LocalDate datePick;
        do {
            try {
                String date = io.readString("What is your order date?"); // add date class to get an integer/string
                datePick = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMddyyyy"));
                return datePick;
            } catch (DateTimeParseException e) {
                again = true;
                io.print("You must in a valid date using MMddyyyy format!");
            }
        } while (again == true);
        return null;
    }

    public Order getOrderInfo(List<Products> productList, List<Tax> stateList) {
        String name;

        do {
            name = io.readString("Please enter in the Name");
            if (name.contains("!") || name.contains("@") || name.contains("/")) {
                io.print("Name cannot contain characters other than ',', '.', 'Aa-Zz', and numbers");
            } else if (name.isBlank()) {
                io.print("Name must not be blank");
            }
        } while (name.contains("!") || name.contains("@") || name.contains("/") || name.isBlank());

        selectState(stateList);
        int stateIndex;
        do {
            try {
                stateIndex = io.readInt("Please enter in the state", 1, stateList.size());
            } catch (NumberFormatException e) {
                stateIndex = 0;
                io.print("Please enter in a number from the choices above!");
            }
        } while (stateIndex < 1);

        selectProduct(productList);
        int productIndex;
        do {
            try {
                productIndex = io.readInt("Please choose the product you want", 1, productList.size());
            } catch (NumberFormatException e) {
                productIndex = 0;
                io.print("Please enter in a number from the choices above!");
            }
        } while (productIndex < 1);

        int areaValue;
        do {
            try {
                areaValue = io.readInt("Please enter in the area");
                if (areaValue < 100) {
                    io.print("The area must be greater than 100");
                }
            } catch (NumberFormatException e) {
                // this is to make it loop again if they dont enter a number;
                areaValue = 0;
                io.print("Must enter in a number!");
            }

        } while (areaValue < 100);

        BigDecimal area = new BigDecimal(areaValue);
        Order order = new Order();
        order.setCustomerName(name);
        order.setState(stateList.get(stateIndex - 1).getStateName());
        order.setProductType(productList.get((productIndex - 1)).getProductType());
        order.setCostPerSquareFoot(productList.get((productIndex - 1)).getCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(productList.get((productIndex - 1)).getLaborCostPerSquareFoot());
        order.setArea(area);
        return order;
    }

    public Order getEditInfo(List<Products> productList, List<Tax> stateList, Order order) {

        String name;

        do {
            name = io.readString("Please enter in the Name");
            if (name.isBlank()) {
                name = order.getCustomerName();
            } else if (name.contains("!") || name.contains("@") || name.contains("/")) {
                io.print("Name cannot contain characters other than ',', '.', 'Aa-Zz', and numbers");
            }
        } while (name.contains("!") || name.contains("@") || name.contains("/"));

//        String state = io.readString("Please enter in the state");
//        if (state.isBlank()) {
//            state = order.getState();
//        }
        selectState(stateList);
        try {
            int stateIndex = io.readInt("Please choose the state you want");

            if (stateIndex < 1 || stateIndex > stateList.size()) {
                order.setState(order.getState());
                order.setTaxRate(order.getTaxRate());//
                io.print("That is not a choice, state is still set to " + order.getState());
            } else {
                order.setState(stateList.get((stateIndex - 1)).getStateName());
                order.setTaxRate(stateList.get((stateIndex - 1)).getTaxRate());
            }

        } catch (NumberFormatException e) {
            //this is to let the function keep running even if NBE is thrown
            order.setState(order.getState());
            order.setTaxRate(order.getTaxRate());
            io.print("That is not a choice, state is still set to " + order.getState());
        }

        selectProduct(productList);
        try {
            int productIndex = io.readInt("Please choose the product you want");

            if (productIndex < 1 || productIndex > productList.size()) {
                order.setProductType(order.getProductType());
                order.setCostPerSquareFoot(order.getCostPerSquareFoot());
                order.setLaborCostPerSquareFoot(order.getLaborCostPerSquareFoot());
                io.print("That is not a choice, product type is still set to " + order.getProductType());
            } else {
                order.setProductType(productList.get((productIndex - 1)).getProductType());
                order.setCostPerSquareFoot(productList.get((productIndex - 1)).getCostPerSquareFoot());
                order.setLaborCostPerSquareFoot(productList.get((productIndex - 1)).getLaborCostPerSquareFoot());
            }

        } catch (NumberFormatException e) {
            //this is to let the function keep running even if NBE is thrown
            order.setProductType(order.getProductType());
            order.setCostPerSquareFoot(order.getCostPerSquareFoot());
            order.setLaborCostPerSquareFoot(order.getLaborCostPerSquareFoot());
            io.print("That is not a choice, product type is still set to " + order.getProductType());
        }

        String areaValue;
        try {
            do {
                areaValue = io.readString("Please enter in the area");
                if (areaValue.isBlank()) {
                    areaValue = order.getArea().toString();
                }
            } while (Integer.parseInt(areaValue) < 100);
        } catch (NumberFormatException e) {
            areaValue = order.getArea().toString();
            io.print("That is not a number, area value is still set to " + order.getArea());
        }
        BigDecimal area = new BigDecimal(areaValue);

        order.setCustomerName(name);
        order.setArea(area);
        return order;
    }

    public void selectProduct(List<Products> productList) {
        int i = 0;
        for (Products product : productList) {
            i++;
            io.print(i + ".) " + product.getProductType());
        }
    }

    public void selectState(List<Tax> stateList) {
        int i = 0;
        for (Tax tax : stateList) {
            i++;
            io.print(i + ".) " + tax.getStateName());
        }
    }

    public int removeChoice() {
        boolean again = false;
        int orderNumber;
        do {
            try {
                orderNumber = io.readInt("What order would you like to remove?");
                return orderNumber;
            } catch (NumberFormatException e) {
                again = true;
                io.print("That is not a number! Please enter your order Number");
            }
        } while (again = true);

        return -1;
    }
    
    public int editChoice() {
        boolean again = false;
        int orderNumber;
        do {
            try {
                orderNumber = io.readInt("What order would you like to edit?");
                return orderNumber;
            } catch (NumberFormatException e) {
                again = true;
                io.print("That is not a number! Please enter your order Number");
            }
        } while (again = true);

        return -1;
    }

    public String isSureDelete() {
        return io.readString("Are you sure you want to delete?");
    }

//    public int getEditChoice() {
//        boolean again = false;
//        int orderNumber;
//        do {
//            try {
//                orderNumber = io.readInt("What order would you like to edit?");
//                return orderNumber;
//            } catch (NumberFormatException e) {
//                again = true;
//                io.print("That is not a number! Please enter your order Number");
//            }
//        } while (again = true);
//        return -1;
//    }

    public String isSureEdit() {
        return io.readString("Are you sure you want to save changes?");
    }

    public String isSureAdd() {
        return io.readString("Are you sure you want to add order?");
    }

    public void displayOrderDoesNotExist() {
        io.print("That order number does not exist");
    }

}
