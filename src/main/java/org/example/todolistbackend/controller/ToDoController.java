package org.example.todolistbackend.controller;

import lombok.AllArgsConstructor;
import org.example.todolistbackend.dto.ToDoDTO;
import org.example.todolistbackend.dto.request.ToDoRequest;
import org.example.todolistbackend.entity.ToDo;
import org.example.todolistbackend.exception.ToDoException;
import org.example.todolistbackend.exception.UserException;
import org.example.todolistbackend.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@AllArgsConstructor
public class ToDoController {

    private ToDoService toDoService;

    @PostMapping("/addtask")
    public ResponseEntity<?> addTask(@RequestBody ToDoDTO taskData, @RequestHeader("Authorization") String token){
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        return ResponseEntity.ok(toDoService.addTask(taskData, jwtToken));
    }

    @GetMapping("/alltasks")
    public ResponseEntity<List<ToDoRequest>> getAllTasks(@RequestHeader("Authorization") String token){
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        return ResponseEntity.ok(toDoService.getAllTasks(jwtToken));
    }

    @DeleteMapping("/deletetask")
    public ResponseEntity<?> deleteTask(@RequestHeader("Authorization") String token, @RequestParam String taskTitle){
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        boolean isDeleted;
        if (toDoService.deleteTask(jwtToken, taskTitle)) isDeleted = true;
        else isDeleted = false;
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/updatetask")
    public ResponseEntity<?> updateTask(@RequestHeader("Authorization") String token, @RequestBody ToDoDTO taskData,
                                        @RequestParam String taskTitle){
        String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
        boolean isUpdated;
        if(toDoService.updateTask(jwtToken, taskData, taskTitle)) isUpdated = true;
        else isUpdated = false;
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
