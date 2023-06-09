package org.example.Controller;

import org.example.Container.ComponentContainer;
import org.example.Dto.ProfileDto;
import org.example.Enum.ProfileRole;
import org.example.Enum.ProfileStatus;
import org.example.Servise.ProfileService;
import org.example.util.GetAction;

import java.time.LocalDateTime;

public class MenuController {
    private ProfileService profileService;
    public void start(){
        boolean t=true;
        while (t){
            show();
            switch (GetAction.getAction()){

                case 1->login();
                case 2->registration();
                case 0 ->t=false;
            }
        }
    }
    public void show(){
        System.out.println("**MENU**");
        System.out.println("1. Login.");
        System.out.println("2. Registration.");
        System.out.println("0. Exit.");

    }
    public void registration(){
        System.out.println("Enter your name: ");
        String name= ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your surname:");
        String surname= ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your phone:");
        String phone= ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your login:");
        String login= ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your password:");
        String password= ComponentContainer.stringScanner.nextLine();

        ProfileDto profileDto= new ProfileDto();
        profileDto.setName(name);
        profileDto.setSurname(surname);
        profileDto.setPhone(phone);
        profileDto.setLogin(login);
        profileDto.setPassword(password);

        ComponentContainer.profileService.register(profileDto);
    }
    public void login(){
        System.out.println("Enter your login: ");
        String login=ComponentContainer.stringScanner.nextLine();
        System.out.println("Enter your password");
        String password=ComponentContainer.stringScanner.nextLine();
        profileService.login(login,password);
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
}
