package com.jsr.restapi.service;

import com.jsr.restapi.entity.User;
import com.jsr.restapi.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(long userId) {
        return userRepository.findByIdAndQuitIsFalse(userId);
    }
}
