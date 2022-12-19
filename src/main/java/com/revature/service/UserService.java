package com.revature.service;

import com.revature.exceptions.PasswordIncorrectException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.persistence.UserDao;
import com.revature.pojos.User;

import java.util.Set;


//we can add in this layer other business logic
//validation - user input
//logging
public class UserService {
    private UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public void registerNewUser(User user) {

        dao.create(user);
    }

    public Set<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public void updateUser(User user) {
        dao.updateUser(user);
    }

    public void deleteUser(Integer userId) {
        dao.deleteUser(userId);
    }

    public void deleteUser(User user) {
        dao.deleteUser(user.getUserId());
    }

    public User authenticateUser(User user) throws UserNotFoundException, PasswordIncorrectException {
        return dao.authenticate(user.getUsername(), user.getPassword());
    }

    public User authenticateUser(String username, String password) throws UserNotFoundException, PasswordIncorrectException {
        return dao.authenticate(username, password);
    }

    public User getUserById(Integer userId) {
        return dao.getUserById(userId);
    }

}
