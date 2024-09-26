package com.manager.controllers;

import com.manager.models.Message;
import com.manager.models.User;
import com.manager.models.UserLogin;
import com.manager.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<Optional<User>> post(@RequestBody @Valid User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(user));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLogin> auth(@RequestBody Optional<UserLogin> user){
        return userService.login(user).map(userAutenticanted -> ResponseEntity.ok(userAutenticanted)).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>>getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/update")
    public  ResponseEntity<Optional<User>> update(@RequestBody @Valid User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.update(user));
    }

    }