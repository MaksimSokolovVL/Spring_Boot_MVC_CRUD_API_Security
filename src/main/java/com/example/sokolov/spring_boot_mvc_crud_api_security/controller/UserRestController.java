package com.example.sokolov.spring_boot_mvc_crud_api_security.controller;

import com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity.User;
import com.example.sokolov.spring_boot_mvc_crud_api_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserForId(id);
    }
}
