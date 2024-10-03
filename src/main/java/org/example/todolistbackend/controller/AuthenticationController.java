package org.example.todolistbackend.controller;


import lombok.AllArgsConstructor;
import org.example.todolistbackend.config.JwtService;
import org.example.todolistbackend.dto.UserLoginDTO;
import org.example.todolistbackend.dto.UserRegisterDTO;
import org.example.todolistbackend.dto.response.LoginResponse;
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
    public ResponseEntity<User> register(@RequestBody UserRegisterDTO input) {
        return ResponseEntity.ok(authenticationService.signup(input));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginDTO input) {
        User authenticatedUser = authenticationService.authenticate(input);
        String token = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
