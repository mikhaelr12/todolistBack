package org.example.todolistbackend.service;

import org.example.todolistbackend.dto.UserRegisterDTO;
import org.example.todolistbackend.entity.User;

public interface AuthenticationService {
    User signup(UserRegisterDTO registerDTO);
}
