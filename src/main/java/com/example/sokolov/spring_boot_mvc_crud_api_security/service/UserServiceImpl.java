package com.example.sokolov.spring_boot_mvc_crud_api_security.service;

import com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity.Role;
import com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity.User;
import com.example.sokolov.spring_boot_mvc_crud_api_security.repository.RoleRepository;
import com.example.sokolov.spring_boot_mvc_crud_api_security.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepo;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepo,
                           RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    @Transactional
    public void saveAndUpdateUser(User user) {
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            for (Role role : user.getRoles()) {
                Role existingRole = roleRepository.findByRoleName(role.getRoleName());
                if (existingRole != null) {
                    roles.add(existingRole);
                } else {
                    roles.add(role);
                }
            }
            user.setRoles(roles);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserForId(long id) {
        User user = null;
        Optional<User> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            user = byId.get();
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkedUserByEmail(String email) {
        return userRepo.findByEmail(email) != null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("There is no user with this LOGIN: %s", username));
        }
        System.err.println("User email: " + user.getEmail() + ", password: " + user.getPassword());
        Hibernate.initialize(user.getRoles());
        return user;
    }
}
