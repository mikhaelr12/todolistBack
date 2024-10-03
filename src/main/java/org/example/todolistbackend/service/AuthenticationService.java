package org.example.todolistbackend.service;

import org.example.todolistbackend.dto.UserLoginDTO;
import org.example.todolistbackend.dto.UserRegisterDTO;
import org.example.todolistbackend.entity.User;

import java.util.Optional;

public interface AuthenticationService {
    void signup(UserRegisterDTO registerDTO);
    User authenticate(UserLoginDTO input);
}
