package com.devsuperior.dscommerce.domain.dto;

import com.devsuperior.dscommerce.domain.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClientDTO {

    private final Long id;
    private final String name;

    public ClientDTO(User user) {
        id = user.getId();
        name = user.getName();
    }

    public static ClientDTO from(User user) {
        return new ClientDTO(user);
    }

}
