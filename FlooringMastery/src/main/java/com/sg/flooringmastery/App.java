/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.FlooringMasteryController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Jose
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
            appContext.scan("com.sg.flooringmastery");
            appContext.refresh();
            
            FlooringMasteryController controller = appContext.getBean("flooringMasteryController", 
                    FlooringMasteryController.class);
            controller.run();
    }
}
