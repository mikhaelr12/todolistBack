package org.example.todolistbackend.exception;

import java.io.Serial;

public class ToDoException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public ToDoException(String message) {
        super(message);
    }

    public ToDoException(){
        super();
    }
}
