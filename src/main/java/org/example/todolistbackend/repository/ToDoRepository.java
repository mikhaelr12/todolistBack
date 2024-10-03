package org.example.todolistbackend.repository;

import org.example.todolistbackend.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}
