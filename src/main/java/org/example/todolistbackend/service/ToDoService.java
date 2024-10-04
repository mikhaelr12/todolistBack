package org.example.todolistbackend.service;

import org.example.todolistbackend.dto.ToDoDTO;
import org.example.todolistbackend.dto.request.ToDoRequest;
import org.example.todolistbackend.entity.ToDo;

import java.util.List;

public interface ToDoService {

    ToDo addTask(ToDoDTO taskInput, String token);

    List<ToDoRequest> getAllTasks(String jwtToken);

    boolean deleteTask(String jwtToken, String taskTitle);

    boolean updateTask(String token, ToDoDTO taskData, String taskTitle);
}
