package org.example.todolistbackend.controller;

import org.example.todolistbackend.dto.ToDoDTO;
import org.example.todolistbackend.service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class ToDoController {

    private ToDoService toDoService;
    @PostMapping("/addtask")
    public ResponseEntity<?> addTask(@RequestBody ToDoDTO taskData, String token){
        return ResponseEntity.ok(toDoService.addTask(taskData, String token));
    }
}
