package org.example.todolistbackend.repository;

import org.example.todolistbackend.dto.request.ToDoRequest;
import org.example.todolistbackend.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> getAllTasksByUserId(Long userId);
}
