package org.example.todolistbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "todos")
@Entity
public class ToDo {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_id_seq")
    @SequenceGenerator(name = "todo_id_seq", sequenceName = "todo_id_seq", allocationSize = 1)
    private Long id;

    @Getter @Setter
    @Column(name = "title", nullable = false)
    private String title;

    @Getter @Setter
    @Column(name = "description")
    private String description;

    @Column(name = "user_id")
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER_TODO"))
    private Long userId;
}
