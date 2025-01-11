package com.bookreview.service;

import com.bookreview.entity.User;

import java.util.List;

public interface UserService {

    //list all users
    List<User> getAllUsers();

    //show user by id
    User getUserById(Long id);

    //create user
    User createUser(User user);

    //update user
    User updateUser(Long id, User user);

    //delete user
    boolean deleteUser(Long id);

}
