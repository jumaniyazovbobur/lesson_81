package org.example.Controller;

import jdk.jshell.execution.Util;
import org.example.Container.ComponentContainer;
import org.example.util.GetAction;

import java.time.LocalDate;

public class UserController {
    public void start() {

        boolean t = true;
        while (t) {
            show();
            switch (GetAction.getAction()) {
                case 1 -> addCard();
                case 2 -> cardList();
                case 3 -> cardChangeStatus();
                case 4 -> deleteCard();
                case 0 -> t = false;
            }
        }
    }


    public void show() {
        System.out.println("*** User  Menu **\n" +
                "    1. Add Card \n" +
                "    2. Card List \n" +
                "    3. Card Change Status\n" +
                "    4. Delete Card (visible_user = false,deleted_user)\n" +
                "    4. ReFill \n" +
                "    5. Transaction\n" +
                "    6. Make Payment" +
                "    0. Exit");
    }

    public void addCard() {
        System.out.println("Enter card number:");
        int cardNum = ComponentContainer.intScanner.nextInt();
        System.out.println("Enter expire date:");
        System.out.println("Enter year:");
        int year = ComponentContainer.intScanner.nextInt();
        System.out.println("Enter month by number: ");
        int month = ComponentContainer.intScanner.nextInt();
        ComponentContainer.userService.addCard(cardNum, year, month);
    }

    public void cardList() {
        ComponentContainer.userService.getCardList(ComponentContainer.profileDto.getPhone());
    }

    public void cardChangeStatus() {
        System.out.println("Enter card number to change status:");
        int cardNum = ComponentContainer.intScanner.nextInt();
        ComponentContainer.userService.cardChangeStatus(cardNum);
    }

    private void deleteCard() {
        System.out.println("Enter card number to delete: ");
        int cardNum = ComponentContainer.intScanner.nextInt();
        ComponentContainer.userService.deleteCard(cardNum);
    }
}
