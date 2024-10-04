package org.example.todolistbackend.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.todolistbackend.dto.UserLoginDTO;
import org.example.todolistbackend.dto.UserRegisterDTO;
import org.example.todolistbackend.entity.User;
import org.example.todolistbackend.exception.UserException;
import org.example.todolistbackend.repository.UserRepository;
import org.example.todolistbackend.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public void signup(UserRegisterDTO input) {
        var user = userRepository.findByUsername(input.getUsername());
        if (user.isPresent()) {
            throw new UserException("Username is already in use");
        }
        User newUser = new User();
        newUser.setUsername(input.getUsername());
        newUser.setPassword(passwordEncoder.encode(input.getPassword()));
        newUser.setEmail(input.getEmail());
        userRepository.save(newUser);
    }

    public User authenticate(UserLoginDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
}
