package org.example;

import org.example.Controller.MenuController;
import org.example.DBInit.DBInit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        DBInit.createTableProfile();
        DBInit.createTableCard();
        DBInit.createTableTerminal();
        // DBInit.createTableTransaction();
//       DBInit.createAdmin();

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        MenuController menuController = (MenuController) context.getBean("menuController");
        menuController.start();
    }
}