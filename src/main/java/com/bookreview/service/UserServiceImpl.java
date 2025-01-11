package com.bookreview.service;

import com.bookreview.entity.User;
import com.bookreview.exception.UserException;
import com.bookreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //list all users
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //show user by id
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UserException("User with ID " + id + " not found", HttpStatus.NOT_FOUND));
    }

    //create user
    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserException("User with email " + user.getEmail() + " already exists", HttpStatus.CONFLICT);
        }
        return userRepository.save(user);
    }

    //update user
    @Override
    public User updateUser(Long id, User user) {
        if (!userRepository.existsById(id)) {
            throw new UserException("User with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        return userRepository.save(user);
    }

    //delete user
    @Override
    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserException("User with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return true;
    }
}
