package org.example.todolistbackend.dto.request;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoRequest {

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String description;
}
