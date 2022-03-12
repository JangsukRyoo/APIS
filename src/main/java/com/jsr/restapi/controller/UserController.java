package com.jsr.restapi.controller;

import com.jsr.restapi.entity.User;
import com.jsr.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/insert")
    public ResponseEntity insertUser(@RequestBody User user){

        if(user != null){
            User newUser = new User();

            newUser.setUserName(user.getUserName());
            newUser.setNickName(user.getNickName());
            newUser.setAccountType(user.getAccountType());

            userService.saveUser(newUser);
        }

        return new ResponseEntity(HttpStatus.OK);
    }


}
