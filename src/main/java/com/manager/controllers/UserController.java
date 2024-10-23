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
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Optional<User>> post(@RequestBody User user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.create(user));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

    @PostMapping("/login")
    public ResponseEntity<UserLogin> auth(@RequestBody @Valid Optional<UserLogin> user){
        return userService.login(user).map(userAutenticanted -> ResponseEntity.ok(userAutenticanted)).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>>getUserById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("/update")
    public  ResponseEntity<Optional<User>> update(@RequestBody @Valid User user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.update(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping("/updatePassword")
    public  ResponseEntity<Optional<User>> updatePassword(@RequestBody Map<String, String> user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updatePassword(user.get("email"), user.get("password")));
    }


}