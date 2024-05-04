package com.tejas.googleauth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.googleauth.collection.User;
import com.tejas.googleauth.collection.UserWithAddedField;
import com.tejas.googleauth.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(path = "/user")
public class UserController {
     @Autowired
     private UserService userService;

     @PostMapping
     public String save(@RequestBody User user) {
         return userService.save(user);
     }

     @GetMapping
     public List<User> findAll() {
         return userService.findAll();
     }
     
     @GetMapping("/age")
     public List<User> getUsersByAgeBetween(@RequestParam Integer minAge, @RequestParam Integer maxAge) {
         return userService.getUsersByAgeBetween(minAge, maxAge);
     }
     
     @GetMapping("/addNewField")
     public List<UserWithAddedField> getUsersByAddingNewField() {
         return userService.getUsersByAddingNewField();
     }
     
}
