/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jose
 */
public class App {

    public static void main(String[] args) {
//        UserIO myIO = new UserIOConsoleImpl();
//        VendingMachineView myView = new VendingMachineView(myIO);
//        VendingMachineDao myDao = new VendingMachineDaoImpl();
//        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
//        VendingMachineServiceLayer service = new VendingMachineServiceLayer(myDao, myAuditDao);
//        VendingMachineController controller = new VendingMachineController(service, myView);
//        controller.run();

    ApplicationContext appContext
            = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    
    VendingMachineController controller = appContext.getBean("controller", VendingMachineController.class);
    controller.run();

    }

}
