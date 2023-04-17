package com.example.sokolov.spring_boot_mvc_crud_api_security.domain.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    public Role(String role) {
        roleName = role;
    }

    @Override
    public String getAuthority() {
        return getRoleName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return roleName.equals(role.roleName);
    }

    @Override
    public int hashCode() {
        return roleName.hashCode();
    }

    @Override
    public String toString() {
        return roleName;
    }
}
