package com.kpit.vehicleavailability.service;

import com.kpit.vehicleavailability.dao.UserDAO;
import com.kpit.vehicleavailability.model.User;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        userDAO = new UserDAO();
    }

    public void registerUser(User user) {
        if (userDAO.userExists(user.getUsername())) {
            throw new RuntimeException("Username already taken");
        }
        userDAO.saveUser(user);
    }

    public User loginUser(String username, String password) {
        return userDAO.getUserByUsernameAndPassword(username, password);
    }
}
