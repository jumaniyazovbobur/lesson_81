package org.example.Repo;

import org.example.Container.ComponentContainer;
import org.example.Dto.CardDto;
import org.example.Enum.CardStatus;
import org.example.util.DBConnection;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRepo {
    public CardDto checkCardByNum(int cardNum) {
        Connection connection = DBConnection.getConnection();
        String sql = "select * from card where card_number=?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, cardNum);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                CardDto cardDto = new CardDto();
                cardDto.setCardNumber(rs.getInt("card_number"));
                cardDto.setBalance(rs.getDouble("balance"));
                cardDto.setCreatedDate(rs.getTimestamp("crated_date").toLocalDateTime());
                cardDto.setExpDate(rs.getDate("exp_date").toLocalDate());
                cardDto.setCardStatus(CardStatus.valueOf(rs.getString("status")));
                cardDto.setUserPhone(rs.getString("phone"));
                return cardDto;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public boolean add_card(CardDto cardDto) {
        Connection connection = DBConnection.getConnection();
        String sql = "insert into card(card_number, crated_date, exp_date, balance, status, phone) values(?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, cardDto.getCardNumber());
            pr.setTimestamp(2, Timestamp.valueOf(cardDto.getCreatedDate()));
            pr.setDate(3, Date.valueOf(cardDto.getExpDate()));
            pr.setDouble(4, cardDto.getBalance());
            pr.setString(5, cardDto.getCardStatus().toString());
            pr.setString(6, cardDto.getUserPhone());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<CardDto> getCardList(String phone) {
        Connection connection = DBConnection.getConnection();
        List<CardDto> cardDtoList = new LinkedList<>();
        String sql = "select * from card where phone=?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, phone);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {

                CardDto cardDto = new CardDto();
                cardDto.setCardNumber(rs.getInt("card_number"));
                cardDto.setBalance(rs.getDouble("balance"));
                cardDto.setCreatedDate(rs.getTimestamp("crated_date").toLocalDateTime());
                cardDto.setExpDate(rs.getDate("exp_date").toLocalDate());
                cardDto.setCardStatus(CardStatus.valueOf(rs.getString("status")));
                cardDto.setUserPhone(rs.getString("phone"));
                cardDtoList.add(cardDto);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return cardDtoList;
    }

    public void update_card(CardDto cardDto) {
        Connection connection = DBConnection.getConnection();
        String sql = "update card set status=? where card_number=? ";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setString(1, cardDto.getCardStatus().toString());
            pr.setInt(2, cardDto.getCardNumber());
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean deleteCard(int cardNum) {
        Connection connection = DBConnection.getConnection();
        String sql = "delete from card where card_number=?";
        try {
            PreparedStatement pr = connection.prepareStatement(sql);
            pr.setInt(1, cardNum);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
