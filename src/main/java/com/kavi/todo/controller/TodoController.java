package com.kavi.todo.controller;

import com.kavi.todo.dto.TodoRequest;
import com.kavi.todo.dto.TodoResponse;
import com.kavi.todo.entity.Todo;
import com.kavi.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public List<TodoResponse> getTodos() {
        return todoService.getTodos();
    }

    @GetMapping("/todos/{id}")
    public TodoResponse getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    @PostMapping("/todos")
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody TodoRequest todo) {
        TodoResponse createdTodo = todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    @DeleteMapping("/todos")
    public void deleteAllTodo() {
        todoService.deleteAllTodos();
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) {
        todoService.deleteTodoById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PutMapping("/todos/{id}")
    public TodoResponse updateTodo(@PathVariable Long id, @RequestBody TodoRequest todo) {
        return todoService.updateTodo(id, todo);
    }
}
