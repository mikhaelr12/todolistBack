package org.example.todolistbackend.service.impl;


import lombok.AllArgsConstructor;
import org.example.todolistbackend.config.JwtService;
import org.example.todolistbackend.dto.ToDoDTO;
import org.example.todolistbackend.entity.ToDo;
import org.example.todolistbackend.entity.User;
import org.example.todolistbackend.exception.ToDoException;
import org.example.todolistbackend.exception.UserException;
import org.example.todolistbackend.repository.ToDoRepository;
import org.example.todolistbackend.repository.UserRepository;
import org.example.todolistbackend.service.ToDoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository toDoRepository;

    private UserRepository userRepository;

    private JwtService jwtService;


    @Override
    public Optional<?> addTask(ToDoDTO taskInput, String token) {

        User user = userRepository.findById(userId);
        if(user == null) {
            throw new UserException("User not found");
        }
        ToDo task = new ToDo();
        task.setTitle(taskInput.getTitle());
        task.setDescription(taskInput.getDescription());
        task.
    }
}
