package com.example.sokolov.spring_boot_mvc_crud_api_security.repository;

import com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);

    @Query("SELECT r FROM Role r")
    Set<Role> findAllRoles();
}
