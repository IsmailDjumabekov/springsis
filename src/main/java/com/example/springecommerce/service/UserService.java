package com.example.springecommerce.service;

import com.example.springecommerce.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Integer id);
}
