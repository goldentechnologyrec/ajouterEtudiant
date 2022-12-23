package com.knf.dev.demo.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knf.dev.demo.backend.entity.User;
import com.knf.dev.demo.backend.exception.InternalServerError;
import com.knf.dev.demo.backend.repository.UserRepository;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User NewUser = new User(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getGroupes(),
                    user.getTelephone());
            userRepository.save(NewUser);
            return new ResponseEntity<>(NewUser, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
}
