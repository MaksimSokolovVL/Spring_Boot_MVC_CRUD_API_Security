package com.example.sokolov.spring_boot_mvc_crud_api_security.service;

import com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity.Role;
import com.example.sokolov.spring_boot_mvc_crud_api_security.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepo;

    public RoleServiceImpl(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getAllRoles() {
        return roleRepo.findAllRoles();
    }

    @Override
    @Transactional(readOnly = true)
    public Role findByRoleName(String roleName) {
        return roleRepo.findByRoleName(roleName);
    }
}
