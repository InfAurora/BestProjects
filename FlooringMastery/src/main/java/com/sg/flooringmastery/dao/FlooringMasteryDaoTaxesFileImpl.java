/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
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
public class FlooringMasteryDaoTaxesFileImpl implements FlooringMasteryDaoTaxes{
    
    private Map<String, Tax> taxes = new HashMap<>();
    String FILE = "Taxes.txt";
    String DELIMITER = ",";

    @Override
    public List<Tax> getTaxes() throws FlooringMasteryPersistenceException {
        loadFile();
        return new ArrayList(taxes.values());
    }
    
    private void loadFile() throws FlooringMasteryPersistenceException{
        Scanner newScan = null;
        try {
            newScan = new Scanner(new FileReader(FILE));
        } catch(FileNotFoundException ex) {
            throw new FlooringMasteryPersistenceException("Taxes file not found!");
        }
        newScan.nextLine();
        while(newScan.hasNextLine()) {
            String line = newScan.nextLine();
            String[] parts = line.split(DELIMITER);
            Tax t = new Tax();
            t.setStateAbbreviation(parts[0]);
            t.setStateName(parts[1]);
            t.setTaxRate(new BigDecimal(parts[2]));
            taxes.put(t.getStateName(), t);
        }
        newScan.close();
    }
}
