package org.example.todolistbackend.controller;

import lombok.AllArgsConstructor;
import org.example.todolistbackend.dto.ToDoDTO;
import org.example.todolistbackend.dto.request.ToDoRequest;
import org.example.todolistbackend.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo/tasks")
@AllArgsConstructor
public class ToDoController {

    private ToDoService toDoService;

    private String getToken(String token){
        return token.startsWith("Bearer ") ? token.substring(7) : token;
    }
    @PostMapping()
    public ResponseEntity<?> addTask(@RequestBody ToDoDTO taskData, @RequestHeader("Authorization") String token){
        String jwtToken = getToken(token);
        return ResponseEntity.ok(toDoService.addTask(taskData, jwtToken));
    }

    @GetMapping()
    public ResponseEntity<List<ToDoRequest>> getAllTasks(@RequestHeader("Authorization") String token){
        String jwtToken = getToken(token);
        return ResponseEntity.ok(toDoService.getAllTasks(jwtToken));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@RequestHeader("Authorization") String token, @PathVariable Long taskId){
        String jwtToken = getToken(token);
        boolean isDeleted;
        if (toDoService.deleteTask(jwtToken, taskId)) isDeleted = true;
        else isDeleted = false;
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@RequestHeader("Authorization") String token, @RequestBody ToDoDTO taskData,
                                        @PathVariable Long taskId){
        String jwtToken = getToken(token);
        boolean isUpdated;
        if(toDoService.updateTask(jwtToken, taskData, taskId)) isUpdated = true;
        else isUpdated = false;
        return isUpdated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
