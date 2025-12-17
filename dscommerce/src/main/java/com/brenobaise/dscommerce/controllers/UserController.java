package com.brenobaise.dscommerce.controllers;

import com.brenobaise.dscommerce.dtos.UserDTO;
import com.brenobaise.dscommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getMe(){
        UserDTO dto = userService.getMe();
        return ResponseEntity.ok(dto);
    }

}
