package org.example.todolistbackend.controller;


import lombok.AllArgsConstructor;
import org.example.todolistbackend.config.JwtService;
import org.example.todolistbackend.dto.UserRegisterDTO;
import org.example.todolistbackend.entity.User;
import org.example.todolistbackend.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterDTO user) {
        return ResponseEntity.ok(authenticationService.signup(user));
    }
}
