/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Products;
import java.util.List;

/**
 *
 * @author Jose
 */
public interface FlooringMasteryDaoProducts {
    List<Products> getProducts() throws FlooringMasteryPersistenceException;
}
