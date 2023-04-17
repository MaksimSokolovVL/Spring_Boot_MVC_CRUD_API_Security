package com.example.sokolov.spring_boot_mvc_crud_api_security.service;

import com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    void saveAndUpdateUser(User user);

    User getUserForId(long id);

    void deleteUser(long id);

    boolean checkedUserByEmail(String email);

    UserDetails loadUserByUsername(String email);

}
