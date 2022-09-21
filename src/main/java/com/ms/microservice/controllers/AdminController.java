package com.ms.microservice.controllers;

import com.ms.microservice.dtos.UserDto;
import com.ms.microservice.security.service.UserDetailsServiceImpl;
import com.ms.microservice.security.users.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/cadastra-user")
    public ResponseEntity<UserModel> cadastraTurma(@RequestBody UserDto userDto){
        UserModel newuser = new UserModel();
        BeanUtils.copyProperties(userDto, newuser);
        userDetailsService.saveUser(newuser);
        return new ResponseEntity<>(newuser, HttpStatus.CREATED);
    }
}
