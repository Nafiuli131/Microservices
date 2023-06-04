package com.example.UserService.service;

import com.example.UserService.entities.User;

import java.util.List;

public interface UserService {

    //create User
    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

    //TODO: update, delete
}
