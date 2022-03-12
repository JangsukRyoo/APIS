package com.jsr.restapi.service;

import com.jsr.restapi.entity.User;

import java.util.Optional;

public interface UserService {

    User saveUser(User user);

    Optional<User> findById(long userId);
}
