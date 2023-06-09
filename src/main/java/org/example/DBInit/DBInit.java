package org.example.DBInit;

import org.example.Container.ComponentContainer;
import org.example.Dto.ProfileDto;
import org.example.Enum.ProfileRole;
import org.example.Enum.ProfileStatus;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class DBInit {
    public static void createAdmin(){
        ProfileDto profileDto= new ProfileDto();
        profileDto.setRole(ProfileRole.ADMIN);
        profileDto.setName("Sanjar");
        profileDto.setSurname("Niyazov");
        profileDto.setLogin("sanjar1990");
        profileDto.setPassword("12345");
        profileDto.setPhone("998908070176");
        profileDto.setCreated_date(LocalDateTime.now());
        profileDto.setStatus(ProfileStatus.ACTIVE);

       boolean result= ComponentContainer.profileRepo.addProfile(profileDto);
    if(result){
        System.out.println("Admin created successfully");
    }else {
        System.out.println("Admin not created");
    }
    }

    public static void createTableProfile(){

        Connection connection= DBConnection.getConnection();
        try {
            Statement statement=connection.createStatement();
           String sql="create table if not exists profile(" +
                   "id bigserial primary key," +
                   "name varchar(100) not null," +
                   "surname varchar(100)," +
                   "phone varchar(13) unique not null," +
                   "password varchar(100) not null," +
                   "login varchar(100) unique not null," +
                   "created_date timestamp default now()," +
                   "status varchar(50) not null," +
                   "role varchar(50) not null  )";
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void createTableCard(){

        Connection connection= DBConnection.getConnection();
        try {
            Statement statement=connection.createStatement();
            String sql="create table if not exists card(" +
                    "card_number bigint primary key," +
                    "crated_date timestamp default now()," +
                    "exp_date date not null," +
                    "balance numeric(10,2) not null," +
                    "status varchar(50) not null," +
                    "phone varchar not null," +
                    "Foreign key (phone) references profile(phone))";
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void createTableTerminal(){

        Connection connection= DBConnection.getConnection();
        try {
            Statement statement=connection.createStatement();
            String sql="create table if not exists terminal(" +
                    "code int primary key," +
                    "address varchar(225)," +
                    "status varchar(50) not null," +
                    "created_date timestamp default now() )";
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void createTableTransaction(){

        Connection connection= DBConnection.getConnection();
        try {
            Statement statement=connection.createStatement();
            String sql="create table if not exists transaction(" +
                    "id bigserial primary key," +
                    "card_number bigint not null," +
                    "amount numeric(10,2) not null," +
                    "terminal_code int not null," +
                    "type transaction_type not null," +
                    "created_date timestamp default now(), " +
                    "Foreign key(card_number) references card(card_number)," +
                    "Foreign key(terminal_code) references terminal(code) )";
            statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
