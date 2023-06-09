package org.example.Servise;

import org.example.Container.ComponentContainer;
import org.example.Dto.CardDto;
import org.example.Dto.ProfileDto;
import org.example.Enum.CardStatus;
import org.example.Enum.ProfileStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AdminService {
    public void createCard(int cardNum, int year ,int month){
        if(ComponentContainer.adminRepo.checkCardByNum(cardNum)!=null){
            System.out.println("This card is already registered by other user!");
        }else {
            CardDto cardDto= new CardDto();
            cardDto.setCardStatus(CardStatus.ACTIVE);
            cardDto.setUserPhone(ComponentContainer.profileDto.getPhone());
            cardDto.setCardNumber(cardNum);
            cardDto.setCreatedDate(LocalDateTime.now());
            cardDto.setExpDate(LocalDate.of(year,month,1));
            cardDto.setBalance(0.0);
            if (ComponentContainer.adminRepo.add_card(cardDto)) {
                System.out.println("You have created card successfully");
            }else {
                System.out.println("something went wrong");
            }

        }
    }

    public void getAllCardList() {
        List<CardDto> cardDtoList=ComponentContainer.adminRepo.getCardList();
        if(cardDtoList!=null){
            cardDtoList.forEach(System.out::println);
        }else {
            System.out.println("there is no card");
        }
    }

    public void updateCard(int cardNum, int year, int month) {
        CardDto cardDto=ComponentContainer.adminRepo.checkCardByNum(cardNum);
        if (cardDto!=null){
            cardDto.setExpDate(LocalDate.of(year,month,1));
            ComponentContainer.adminRepo.updateCard(cardDto);
            System.out.println("Card was updated Successfully");
        }else {
            System.out.println("Not found card with this number!");
        }
    }

    public void changeStatus(int cardNum, String status) {
        CardDto cardDto=ComponentContainer.adminRepo.checkCardByNum(cardNum);
        if (cardDto!=null){
            cardDto.setCardStatus(CardStatus.valueOf(status));
            ComponentContainer.adminRepo.update_card(cardDto);
            System.out.println("Card status was changed to: "+status);

        }else {
            System.out.println("Not found card with this number!");
        }
    }

    public void deleteCard(int cardNum) {
        CardDto cardDto=ComponentContainer.adminRepo.checkCardByNum(cardNum);
        if(cardDto!=null){
            boolean result=ComponentContainer.adminRepo.deleteCard(cardNum);
            if(result){
                System.out.println("Card was deleted successfully");
            }else {
                System.out.println("Something went wrong");
            }
        }else {
            System.out.println("Card was not found!");
        }
    }

    public void getAllProfile() {
        List<ProfileDto> profileDtoList=ComponentContainer.adminRepo.getProfileList();
        if(profileDtoList !=null){
            profileDtoList.forEach(System.out::println);
        }else {
            System.out.println("Profile was not found");
        }
    }

    public void changeProfileStatus(String phone, String status) {
        ProfileDto profileDto=ComponentContainer.adminRepo.getProfile(phone);
        if(profileDto!=null){
            profileDto.setStatus(ProfileStatus.valueOf(status));
            System.out.println("Profile status was changed to: "+status);
            ComponentContainer.adminRepo.updateProfile(profileDto);
        }else {
            System.out.println("This profile was not found");
        }
    }
}

