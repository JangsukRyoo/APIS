package com.jsr.restapi.entity;

import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    User save(User user);

    Optional<User> findByIdAndQuitIsFalse(long userId);

}
