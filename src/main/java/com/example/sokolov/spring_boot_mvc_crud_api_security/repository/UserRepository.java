package com.example.sokolov.spring_boot_mvc_crud_api_security.repository;

import com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
