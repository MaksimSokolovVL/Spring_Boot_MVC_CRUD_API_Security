package com.example.sokolov.spring_boot_mvc_crud_api_security.controller;


import com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity.Role;
import com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity.User;
import com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity.UserRole;
import com.example.sokolov.spring_boot_mvc_crud_api_security.service.RoleService;
import com.example.sokolov.spring_boot_mvc_crud_api_security.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    private final RoleService roleService;
    private final UserService userService;

    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        if (user == null) {
            throw new RuntimeException("User the null");
        }

        Set<Role> allRoles = roleService.getAllRoles();

        if (allRoles.size() == 0) {
            user.setRoles(Collections.singleton(createdUserRole()));
        } else {
            Role byRoleName = roleService.findByRoleName(UserRole.USER.getName());
            user.setRoles(Collections.singleton(byRoleName != null ? byRoleName : createdUserRole()));
        }

        userService.saveAndUpdateUser(user);
        return user;
    }

    @PutMapping("/users")
    public User editUser(@RequestBody User user) {

        if (userService.getUserForId(user.getId()) == null) {
            throw new RuntimeException(String.format("User with ID %d does not exist in database", user.getId()));
        }
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            throw new RuntimeException("User roles  ->  NULL or EMPTY");
        }

        userService.saveAndUpdateUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return String.format("Employee with ID = %d was deleted", id);
    }

    @GetMapping("/user/{email}")
    public String checkedUserByEmail(@PathVariable String email) {
        return userService.checkedUserByEmail(email) ? "YES" : "NO";

    }

    private Role createdUserRole() {
        return new Role(UserRole.USER.getName());
    }
}
