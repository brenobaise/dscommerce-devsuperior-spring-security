package com.brenobaise.dscommerce.services;

import com.brenobaise.dscommerce.entities.User;
import com.brenobaise.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserService userService;

    public void validateSelfOrAdmin(Long userId){
        User loggedUser = userService.authenticated();
        if(!loggedUser.hasRole("ROLE_ADMIN") && !loggedUser.getId().equals(userId)){
            throw new ForbiddenException("Access Denied");

        }
    }
}
