/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Products;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class FlooringMasteryDaoProductsFileImpl implements FlooringMasteryDaoProducts {

    private Map<String, Products> products = new HashMap<>();

    String FILE = "Products.txt";
    final String DELIMITER = ",";

    @Override
    public List<Products> getProducts() throws FlooringMasteryPersistenceException {
        loadFile();
        return new ArrayList(products.values());
    }

    private void loadFile() throws FlooringMasteryPersistenceException {
        Scanner newScan = null;
        try {
            newScan = new Scanner(new FileReader(FILE));
        } catch (FileNotFoundException ex) {
            throw new FlooringMasteryPersistenceException("Products file not found!");
        }
        newScan.nextLine();
        while (newScan.hasNextLine()) {
            String line = newScan.nextLine();
            String[] parts = line.split(DELIMITER);
            Products p = new Products();
            p.setProductType(parts[0]);
            p.setCostPerSquareFoot(new BigDecimal(parts[1]));
            p.setLaborCostPerSquareFoot(new BigDecimal(parts[2]));
            products.put(p.getProductType(), p);
        }
        newScan.close();
    }
}
