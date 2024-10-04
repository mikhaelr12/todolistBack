package org.example.todolistbackend.service.impl;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todolistbackend.config.JwtService;
import org.example.todolistbackend.dto.ToDoDTO;
import org.example.todolistbackend.dto.request.ToDoRequest;
import org.example.todolistbackend.entity.ToDo;
import org.example.todolistbackend.entity.User;
import org.example.todolistbackend.exception.ToDoException;
import org.example.todolistbackend.repository.ToDoRepository;
import org.example.todolistbackend.repository.UserRepository;
import org.example.todolistbackend.service.ToDoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository toDoRepository;

    private UserRepository userRepository;

    private JwtService jwtService;


    private User getUser(String jwtToken){
        String username = jwtService.extractUsername(jwtToken);
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public ToDo addTask(ToDoDTO taskInput, String token) {
        User user = getUser(token);
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
        User user = getUser(jwtToken);
        log.info("User ID: {}", user.getId());
        List<ToDo> allTasks = toDoRepository.getAllTasksByUserId(user.getId());
        return allTasks.stream().map(task -> ToDoRequest.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .build()
        ).collect(Collectors.toList());
    }

    @Override
    public boolean deleteTask(String jwtToken, Long taskId) {
        User user = getUser(jwtToken);
        List<ToDo> getAllTasks = toDoRepository.getAllTasksByUserId(user.getId());
        ToDo taskToDelete = getAllTasks.stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new ToDoException("Task not found"));
        toDoRepository.delete(taskToDelete);
        return true;
    }

    @Override
    public boolean updateTask(String token, ToDoDTO taskData, Long taskId) {
        User user = getUser(token);
        List<ToDo> getAllTasks = toDoRepository.getAllTasksByUserId(user.getId());
        ToDo taskToEdit = getAllTasks.stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .orElseThrow(() -> new ToDoException("No task found"));
        if(!taskData.getDescription().isEmpty()){
            taskToEdit.setDescription(taskData.getDescription());
            return true;
        }
        return false;
    }
}
