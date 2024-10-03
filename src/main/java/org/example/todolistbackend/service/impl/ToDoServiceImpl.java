package org.example.todolistbackend.service.impl;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todolistbackend.config.JwtService;
import org.example.todolistbackend.dto.ToDoDTO;
import org.example.todolistbackend.dto.request.ToDoRequest;
import org.example.todolistbackend.entity.ToDo;
import org.example.todolistbackend.entity.User;
import org.example.todolistbackend.exception.UserException;
import org.example.todolistbackend.repository.ToDoRepository;
import org.example.todolistbackend.repository.UserRepository;
import org.example.todolistbackend.service.ToDoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository toDoRepository;

    private UserRepository userRepository;

    private JwtService jwtService;


    @Override
    public ToDo addTask(ToDoDTO taskInput, String token) {
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("User not found"));
       return toDoRepository.save(
               ToDo.builder()
                       .title(taskInput.getTitle())
                       .description(taskInput.getDescription())
                       .userId(user.getId())
                       .build()
       );
    }

    @Override
    public List<ToDoRequest> getAllTasks(String jwtToken) {
        String username = jwtService.extractUsername(jwtToken);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserException("User not found"));
        List<ToDo> allTasks = toDoRepository.getAllTasksByUserId(user.getId());
        return allTasks.stream().map(task -> ToDoRequest.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .build()
        ).collect(Collectors.toList());
    }
}
