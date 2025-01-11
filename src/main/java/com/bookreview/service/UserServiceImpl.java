package com.bookreview.service;

import com.bookreview.entity.User;
import com.bookreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    //create user
    @Override
    public User createUser(User user){
        return userRepository.save(user);
    }

    //update user
    @Override
    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    //delete user
    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
