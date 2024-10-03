package org.example.todolistbackend.dto;

import lombok.Getter;
import lombok.Setter;

public class UserLoginDTO {

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;
}
