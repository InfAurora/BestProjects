/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jose
 */
@Component
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

    private final Map<Integer, Order> orders = new HashMap<>();

    String FILE;
    final String DELIMITER = ",";
    int lineCounter = 0;
    
    public FlooringMasteryDaoFileImpl () {
    }
    
    public FlooringMasteryDaoFileImpl(String itemTextFile) {
        FILE = itemTextFile; //needed for tests
    }

    @Override
    public Order addOrder(int orderNumber, Order order, LocalDate date) throws FlooringMasteryPersistenceException {
        //I believe i have to reinstatiate FILE in an if statement here to create a 
        //new file based on the currentDate
        try {
            loadFile(date);
            //order.setOrderNumber(lineCounter);
        } catch (FlooringMasteryPersistenceException e) {
            //the write file is now going to create a file if none exists.
        }
        //int orderNum = orders.keySet().size();
        //order.setOrderNumber(orderNum);
        Order newOrder = orders.put(orderNumber, order);
        writeFile(date);
        return newOrder;
    }

    @Override
    public Order removeOrder(int orderNumber, LocalDate date) throws FlooringMasteryPersistenceException {
        loadFile(date);
        Order order = orders.remove(orderNumber);
        writeFile(date);
        return order;
    }

    @Override
    public Order editOrder(int orderNumber, LocalDate date, Order order) throws FlooringMasteryPersistenceException {
        loadFile(date);
        order = orders.replace(orderNumber, order);
        writeFile(date);
        return order;
    }

    @Override
    public List<Order> listOrders(LocalDate date) throws FlooringMasteryPersistenceException {
        loadFile(date);
        return new ArrayList(orders.values());
    }

    @Override
    public void exportAll() throws FlooringMasteryPersistenceException {
        PrintWriter pw = null;
        String data = "DataExport.txt";
        File[] folder = new File("Orders").listFiles();
        try {
            pw = new PrintWriter(new FileWriter(data));
        } catch (IOException ex) {
            throw new FlooringMasteryPersistenceException("Error writing to file!");
        }

        orders.clear();
        for (File f : folder) {
            
            String dateString = f.toString().substring(14, 22);
            LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MMddyyyy"));
            List<Order> orderList = this.listOrders(date);
            String fileDate = date.format(DateTimeFormatter.ofPattern("MM-dd-yyy"));
            for (Order o : orderList) {
                pw.println(o.getOrderNumber() + DELIMITER + o.getCustomerName() + DELIMITER + o.getState()
                        + DELIMITER + o.getTaxRate() + DELIMITER + o.getProductType() + DELIMITER + o.getArea()
                        + DELIMITER + o.getCostPerSquareFoot() + DELIMITER + o.getLaborCostPerSquareFoot()
                        + DELIMITER + o.getMaterialCost() + DELIMITER + o.getLaborCost()
                        + DELIMITER + o.getTax() + DELIMITER + o.getTotal() + DELIMITER + fileDate);
            }
            orders.clear();
        }
        pw.flush();
        pw.close();

    }

//    private List<Order> listOrdersExport(LocalDate date) throws FlooringMasteryPersistenceException {
//        loadFile(date);
//        return new ArrayList(orders.values());
//    } not used??????

    private void loadFile(LocalDate date/*pass in date paramater*/) throws FlooringMasteryPersistenceException {
        //date = LocalDate.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        FILE = "Orders/Orders_" + formattedDate + ".txt";
        Scanner newScan = null;

        try {
            newScan = new Scanner(new FileReader(FILE));
        } catch (FileNotFoundException ex) {
            throw new FlooringMasteryPersistenceException("Order not found for that date!");
        }
        //orders.clear();
        while (newScan.hasNextLine()) {
            String line = newScan.nextLine();
            String[] parts = line.split(DELIMITER);
            Order o = new Order();
            o.setOrderNumber(Integer.parseInt(parts[0]));
            o.setCustomerName(parts[1].replace("/", ","));
            o.setState(parts[2]);
            o.setTaxRate(new BigDecimal(parts[3]));
            o.setProductType(parts[4]);
            o.setArea(new BigDecimal(parts[5]));
            o.setCostPerSquareFoot(new BigDecimal(parts[6]));
            o.setLaborCostPerSquareFoot(new BigDecimal(parts[7]));
            o.setMaterialCost(new BigDecimal(parts[8]));
            o.setLaborCost(new BigDecimal(parts[9]));
            o.setTax(new BigDecimal(parts[10]));
            o.setTotal(new BigDecimal(parts[11]));

            orders.put(o.getOrderNumber(), o);
        }
        newScan.close();

    }

    private void writeFile(LocalDate date) throws FlooringMasteryPersistenceException {
        PrintWriter pw = null;
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        FILE = "Orders/Orders_" + formattedDate + ".txt";

        try {
            pw = new PrintWriter(new FileWriter(FILE));
        } catch (IOException ex) {
            throw new FlooringMasteryPersistenceException("Error writing to file!");
        }

        List<Order> orderList = this.listOrders(date);
        for (Order o : orderList) {
            pw.println(o.getOrderNumber() + DELIMITER + o.getCustomerName().replace(",", "/") + DELIMITER + o.getState()
                    + DELIMITER + o.getTaxRate() + DELIMITER + o.getProductType() + DELIMITER + o.getArea()
                    + DELIMITER + o.getCostPerSquareFoot() + DELIMITER + o.getLaborCostPerSquareFoot()
                    + DELIMITER + o.getMaterialCost() + DELIMITER + o.getLaborCost()
                    + DELIMITER + o.getTax() + DELIMITER + o.getTotal());
        }
        pw.flush();
        pw.close();
    }

    @Override
    public Order getOneOrder(int orderNumber, LocalDate date) throws FlooringMasteryPersistenceException {
        loadFile(date);
        return orders.get(orderNumber);
    }

}
