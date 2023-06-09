package org.example.Servise;

import org.example.Container.ComponentContainer;
import org.example.Dto.CardDto;
import org.example.Enum.CardStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserService {
    public void addCard(int cardNum, int year, int month) {
        if (ComponentContainer.userRepo.checkCardByNum(cardNum) != null) {
            System.out.println("This card is already registered by other user!");
            return;
        }
        // save
        CardDto cardDto = new CardDto();
        cardDto.setCardStatus(CardStatus.ACTIVE);
        cardDto.setUserPhone(ComponentContainer.profileDto.getPhone());
        cardDto.setCardNumber(cardNum);
        cardDto.setCreatedDate(LocalDateTime.now());
        cardDto.setExpDate(LocalDate.of(year, month, 1));
        cardDto.setBalance(0.0);
        if (ComponentContainer.userRepo.add_card(cardDto)) {
            System.out.println("You have created card successfully");
        } else {
            System.out.println("something went wrong");
        }
    }

    public void getCardList(String phone) {
        List<CardDto> cardDtoList = ComponentContainer.userRepo.getCardList(phone);
        if (!cardDtoList.isEmpty()) {
            cardDtoList.forEach(System.out::println);
        } else {
            System.out.println("you don't have any cards");
        }
    }

    public void cardChangeStatus(int cardNum) {
        CardDto cardDto = ComponentContainer.userRepo.checkCardByNum(cardNum);
        if (cardDto != null) {
            if (cardDto.getCardStatus().equals(CardStatus.ACTIVE)) {
                cardDto.setCardStatus(CardStatus.NOT_ACTIVE);
                System.out.println("Card status Changed to No Active");
            } else if (cardDto.getCardStatus().equals(CardStatus.NOT_ACTIVE)) {
                cardDto.setCardStatus(CardStatus.ACTIVE);
                System.out.println("Card status Changed to Active");
            }
            ComponentContainer.userRepo.update_card(cardDto);

        } else {
            System.out.println("Not found card with this number!");
        }
    }

    public void deleteCard(int cardNum) {
        CardDto cardDto = ComponentContainer.userRepo.checkCardByNum(cardNum);
        if (cardDto != null) {
            System.out.println("Card was not found!");
            return;
        }
        // check owner
        if (!cardDto.getUserPhone().equals(ComponentContainer.profileDto.getPhone())) {
            System.out.println("Card not belongs to you mazgi!");
            return;
        }
        // delete
        boolean result = ComponentContainer.userRepo.deleteCard(cardNum);
        if (result) {
            System.out.println("Card was deleted successfully");
        } else {
            System.out.println("Something went wrong");
        }
    }
}
