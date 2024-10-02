package org.example.todolistbackend.dto;

import lombok.Getter;
import lombok.Setter;

public class UserRegisterDTO {

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String email;
}
