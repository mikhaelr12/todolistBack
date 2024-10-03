package org.example.todolistbackend.service;

import org.example.todolistbackend.dto.ToDoDTO;

import java.util.Optional;

public interface ToDoService {

Optional<?> addTask(ToDoDTO taskInput, Long userId);

}
