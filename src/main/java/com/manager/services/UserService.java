package com.manager.services;

import com.manager.models.User;
import com.manager.models.UserLogin;
import com.manager.repositories.UserRepository;
import com.manager.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public Optional<User> create(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if(existingUser.isPresent()){
            return Optional.empty();
        }else {
            user.setPassword(criptPassword(user.getPassword()));
            return Optional.of(userRepository.save(user));
        }
    }

    public Optional<User> update(User user) {
        if (userRepository.findById(user.getId()).isPresent()) {

            Optional<User> searchUser = userRepository.findByUsername(user.getUsername());

            if ((searchUser.isPresent()) && (searchUser.get().getId() != user.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Existented User!", null);
            }

            user.setPassword(criptPassword(user.getPassword()));

            return Optional.ofNullable(userRepository.save(user));

        }
        return Optional.empty();
    }

    public Optional<User> updatePassword(String email, String password) {
        Optional<User> searchUser = userRepository.findByEmail(email);


        if (searchUser.isPresent()) {
            System.out.println("Email encontrado!");
            searchUser.get().setPassword(criptPassword(password));

            return Optional.ofNullable(userRepository.save(searchUser.get()));

        }
        return Optional.empty();
    }


    public Optional<UserLogin> login(Optional<UserLogin> userLogin) {

        var crendencials = new UsernamePasswordAuthenticationToken(userLogin.get().getUsername(), userLogin.get().getPassword());

        Authentication authentication = authenticationManager.authenticate(crendencials);

        if (authentication.isAuthenticated()){

            Optional<User> user = userRepository.findByUsername(userLogin.get().getUsername());

            if (user.isPresent()){
                userLogin.get().setId(user.get().getId());
                userLogin.get().setName(user.get().getName());
                userLogin.get().setPicture(user.get().getPicture());
                userLogin.get().setToken(createToken(userLogin.get().getUsername()));
                userLogin.get().setPassword("");

                return userLogin;
            }
        }
        return Optional.empty();
    }

    private String criptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    private String createToken(String username) {
        return "Bearer " + jwtService.generateToken(username);
    }

    public Optional<User> getById(Integer id) {
        return userRepository.findById(id);
    }
}
