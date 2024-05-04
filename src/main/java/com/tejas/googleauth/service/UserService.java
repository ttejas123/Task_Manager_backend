package com.tejas.googleauth.service;

import java.util.List;

import com.tejas.googleauth.collection.User;
import com.tejas.googleauth.collection.UserWithAddedField;

public interface UserService {

    String save(User user);

    List<User> findAll();

    List<User> getUsersByAgeBetween(Integer minAge, Integer maxAge);

    List<UserWithAddedField> getUsersByAddingNewField();


}
