package org.example.todolistbackend.dto.response;

import lombok.Getter;
import lombok.Setter;

public class LoginResponse {

    @Getter @Setter
    private String token;

    @Getter @Setter
    private Long expiresIn;

}
