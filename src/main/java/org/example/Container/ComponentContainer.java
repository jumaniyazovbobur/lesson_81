package org.example.Container;

import org.example.Controller.AdminController;
import org.example.Controller.UserController;
import org.example.Dto.ProfileDto;
import org.example.Repo.AdminRepo;
import org.example.Repo.ProfileRepo;
import org.example.Repo.UserRepo;
import org.example.Servise.*;
import org.example.Servise.sms.EskizSmsService;
import org.example.Servise.sms.SmsService;

import java.util.Scanner;

public class ComponentContainer {
    public static Scanner stringScanner = new Scanner(System.in);
    public static Scanner intScanner = new Scanner(System.in);
    public static ProfileService profileService = new ProfileService();
    public static ProfileRepo profileRepo = new ProfileRepo();
    public static UserController userController = new UserController();
    public static UserService userService = new UserService();
    public static UserRepo userRepo = new UserRepo();
    public static ProfileDto profileDto = null;
    public static AdminController adminController = new AdminController();
    public static AdminService adminService = new AdminService();
    public static AdminRepo adminRepo = new AdminRepo();
    //    public static SmsService smsService = new SmsService();
//    public static EskizSmsService eskizSmsService = new EskizSmsService();
    public static SmsService smsService = new EskizSmsService();
//    public static SmsService smsService = new SmsServiceImp();
}
