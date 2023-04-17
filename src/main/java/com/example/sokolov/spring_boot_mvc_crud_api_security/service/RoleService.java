package com.example.sokolov.spring_boot_mvc_crud_api_security.service;

import com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getAllRoles();

    Role findByRoleName(String roleName);
}
