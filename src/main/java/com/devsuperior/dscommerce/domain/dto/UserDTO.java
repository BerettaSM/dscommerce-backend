package com.devsuperior.dscommerce.domain.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.devsuperior.dscommerce.domain.entities.Role;
import com.devsuperior.dscommerce.domain.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;

    private final List<String> roles = new ArrayList<>();

    public UserDTO(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        phone = user.getPhone();
        birthDate = user.getBirthDate();
        for (Role role: user.getAuthorities()) {
            addRole(role.getAuthority());
        }
    }

    public void addRole(String roleName) {
        roles.add(roleName);
    }

}
