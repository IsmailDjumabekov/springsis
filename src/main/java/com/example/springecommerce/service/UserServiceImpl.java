package com.example.springecommerce.service;

import com.example.springecommerce.model.User;
import com.example.springecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

}
