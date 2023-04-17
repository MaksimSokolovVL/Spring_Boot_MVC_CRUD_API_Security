package com.example.sokolov.spring_boot_mvc_crud_api_security.domain;



import com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity.Role;
import com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

public class JoinedRolesUtil {

    public static String joinRoles(User user){
        List<String> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRoleName().replace("ROLE_", ""));
        }
        return String.join(", ", roles);
    }
}
