package com.revature.persistence;

import com.revature.exceptions.PasswordIncorrectException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.pojos.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;


public class UserDao {
    private Connection connection;

    public UserDao() {
        this.connection = ConnectionManager.getConnection();
    }

    public void create(User user) {
        try {
            String sql = "INSERT INTO users (first_name, last_name, username, password) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());

            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                user.setUserId(rs.getInt("user_id"));
                System.out.println("DEBUG - auto generated key: " + user.getUserId());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public User authenticate(String username, String password) throws UserNotFoundException, PasswordIncorrectException {
        try {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            //should only ever give one result...

            if(!rs.next()) {
                throw new UserNotFoundException("This username was not found");
            }

            User user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"));

            if(user.getPassword().equals(password)) {
                return user;
            }

            throw new PasswordIncorrectException("That password is not correct");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(Integer userId) {
        User user = new User();
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    public Set<User> getAllUsers() {
        Set<User> results = new HashSet<>();
        try {
            String sql = "SELECT * FROM users";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                results.add( new User(rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }







    public void updateUser(User user) {
        try {
            String sql = "UPDATE users SET first_name = ?, last_name = ?, username = ?, password = ? WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(5, user.getUserId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteUser(Integer userId) {
        try {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userId);

            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
