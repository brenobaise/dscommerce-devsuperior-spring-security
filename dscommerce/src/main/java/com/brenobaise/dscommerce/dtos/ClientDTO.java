package com.brenobaise.dscommerce.dtos;

import com.brenobaise.dscommerce.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long id;
    private String name;

    public ClientDTO(User entity){
        id = entity.getId();
        name = entity.getName();
    }
}

