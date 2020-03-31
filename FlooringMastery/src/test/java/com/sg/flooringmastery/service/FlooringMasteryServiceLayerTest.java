/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryDao;
import com.sg.flooringmastery.dao.FlooringMasteryDaoMock;
import com.sg.flooringmastery.dao.FlooringMasteryDaoProducts;
import com.sg.flooringmastery.dao.FlooringMasteryDaoProductsFileImpl;
import com.sg.flooringmastery.dao.FlooringMasteryDaoTaxes;
import com.sg.flooringmastery.dao.FlooringMasteryDaoTaxesFileImpl;
import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Jose
 */
public class FlooringMasteryServiceLayerTest {

    FlooringMasteryDao dao = new FlooringMasteryDaoMock();
    FlooringMasteryDaoProducts dao2 = new FlooringMasteryDaoProductsFileImpl();
    FlooringMasteryDaoTaxes dao3 = new FlooringMasteryDaoTaxesFileImpl();
    FlooringMasteryServiceLayer service = new FlooringMasteryServiceLayer(dao, dao2, dao3);

    public FlooringMasteryServiceLayerTest() {
    }

//    @BeforeEach
//    public void setUp() throws Exception{
//        List<Tax> taxList = dao3.getTaxes();
//        taxList.add(e);
//    }
//    
    @Test
    public void testAddOrder() throws Exception {
    }

    @Test
    public void testGetTax() throws Exception {

        String state1 = "Washington";
        String state2 = "California";
        String state3 = "Texas";
        String state4 = "Kentucky";

        BigDecimal tax1 = service.getTax(state1);
        BigDecimal tax2 = service.getTax(state2);
        BigDecimal tax3 = service.getTax(state3);
        BigDecimal tax4 = service.getTax(state4);

        assertEquals(dao3.getTaxes().get(3).getStateName(), state1);
        assertEquals(dao3.getTaxes().get(2).getStateName(), state2);
        assertEquals(dao3.getTaxes().get(1).getStateName(), state4);
        assertEquals(dao3.getTaxes().get(0).getStateName(), state3);

        assertTrue(tax1.equals(new BigDecimal("9.25")));
        assertTrue(tax2.equals(new BigDecimal("25.00")));
        assertTrue(tax3.equals(new BigDecimal("4.45")));
        assertTrue(tax4.equals(new BigDecimal("6.00")));
    }

    @Test
    public void testGetMaterialCost() throws Exception {
        String carpet = "Carpet";
        String laminate = "Laminate";
        String tile = "Tile";
        String wood = "Wood";

        Order order1 = new Order();
        order1.setProductType(carpet);
        order1.setArea(new BigDecimal(10));

        Order order2 = new Order();
        order2.setProductType(laminate);
        order2.setArea(new BigDecimal(10));

        Order order3 = new Order();
        order3.setProductType(tile);
        order3.setArea(new BigDecimal(10));

        Order order4 = new Order();
        order4.setProductType(wood);
        order4.setArea(new BigDecimal(10));

        BigDecimal prod1 = service.getMaterialCost(order1);
        BigDecimal prod2 = service.getMaterialCost(order2);
        BigDecimal prod3 = service.getMaterialCost(order3);
        BigDecimal prod4 = service.getMaterialCost(order4);

        assertEquals(prod1, new BigDecimal("22.50"));
        assertEquals(prod2, new BigDecimal("17.50"));
        assertEquals(prod3, new BigDecimal("35.00"));
        assertEquals(prod4, new BigDecimal("51.50"));
    }

    @Test
    public void testGetLaborCost() throws Exception {
        String carpet = "Carpet";
        String laminate = "Laminate";
        String tile = "Tile";
        String wood = "Wood";

        Order order1 = new Order();
        order1.setProductType(carpet);
        order1.setArea(new BigDecimal(10));

        Order order2 = new Order();
        order2.setProductType(laminate);
        order2.setArea(new BigDecimal(10));

        Order order3 = new Order();
        order3.setProductType(tile);
        order3.setArea(new BigDecimal(10));

        Order order4 = new Order();
        order4.setProductType(wood);
        order4.setArea(new BigDecimal(10));

        BigDecimal prod1 = service.getLaborCost(order1);
        BigDecimal prod2 = service.getLaborCost(order2);
        BigDecimal prod3 = service.getLaborCost(order3);
        BigDecimal prod4 = service.getLaborCost(order4);

        assertEquals(prod1, new BigDecimal("21.00"));
        assertEquals(prod2, new BigDecimal("21.00"));
        assertEquals(prod3, new BigDecimal("41.50"));
        assertEquals(prod4, new BigDecimal("47.50"));
    }

    @Test
    public void testGetTaxTotal() throws Exception {
        Order order = new Order();
        order.setArea(BigDecimal.TEN);
        order.setTaxRate(new BigDecimal("4.45"));
        order.setProductType("Carpet");
        
        BigDecimal taxTotal = service.getTaxTotal(order);
        
        assertEquals(taxTotal, new BigDecimal("1.935750"));
    }

    @Test
    public void testGetTotal() throws Exception {
        Order order = new Order();
        order.setArea(BigDecimal.TEN);
        order.setTaxRate(new BigDecimal("4.45"));
        order.setProductType("Carpet");
        order.setTax(new BigDecimal("1.935750"));
        
        BigDecimal total = service.getTotal(order);
        
        assertEquals(total, new BigDecimal("45.435750"));
    }

    @Test
    public void editOrder() throws Exception {
//        Order order = new Order();
        int orderNumber = 4;
        String state = "California";
        LocalDate date = LocalDate.now();
        
        Order editedOrder = new Order(1, "Jose", "Texas", new BigDecimal("4.45"),
                "Tile", new BigDecimal(300), new BigDecimal("3.50"), new BigDecimal("4.15"),
                new BigDecimal("1050.00"), new BigDecimal("1245.00"),
                new BigDecimal("102.127500"), new BigDecimal("2397.127500"));
        
        
        Order edit = service.editOrder(editedOrder, state, date, orderNumber);
        
        assertTrue(edit.getOrderNumber().equals(orderNumber));
        
    }

}
