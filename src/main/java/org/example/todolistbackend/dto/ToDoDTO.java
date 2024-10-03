package org.example.todolistbackend.dto;

import lombok.Getter;
import lombok.Setter;

public class ToDoDTO {

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String description;
}
