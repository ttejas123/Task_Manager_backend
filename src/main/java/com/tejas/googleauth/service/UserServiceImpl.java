package com.tejas.googleauth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tejas.googleauth.collection.User;
import com.tejas.googleauth.collection.UserWithAddedField;
import com.tejas.googleauth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public String save(User user) {
        return userRepository.save(user).get_id();
    }
    
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersByAgeBetween(Integer minAge, Integer maxAge) {
        return userRepository.getUsersByAgeBetween(minAge, maxAge);
    }

    @Override
    public List<UserWithAddedField> getUsersByAddingNewField() {
        return userRepository.getUsersByAddingNewField();
    }
    
    
}
