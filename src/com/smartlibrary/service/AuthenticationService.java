package com.smartlibrary.service;

import com.smartlibrary.model.User;
import com.smartlibrary.utils.FileUtils;

import java.util.List;

public class AuthenticationService {
    private List<User> users;

    public AuthenticationService() {
        users = FileUtils.loadUsers();
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
