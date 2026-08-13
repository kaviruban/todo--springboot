package com.kavi.todo.service;

import com.kavi.todo.dto.TodoRequest;
import com.kavi.todo.dto.TodoResponse;
import com.kavi.todo.entity.Todo;
import com.kavi.todo.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    public List<TodoResponse> getTodos() {
        List<Todo> todos = todoRepository.findAll();

        return todos.stream()
                .map(todo -> new TodoResponse(
                        todo.getId(),
                        todo.getTitle(),
                        todo.getCompleted()
                ))
                .toList();
    }

    public TodoResponse getTodoById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found."));
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getCompleted()
        );
    }

    public TodoResponse createTodo(TodoRequest todo) {
        Todo newTodo = new Todo(todo.getTitle(), todo.isCompleted());
        Todo saveTodo = todoRepository.save(newTodo);
        return new TodoResponse(
                saveTodo.getId(),
                saveTodo.getTitle(),
                saveTodo.getCompleted()
        );
    }

    public void deleteAllTodos() {
        todoRepository.deleteAll();
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }

    public TodoResponse updateTodo(Long id, TodoRequest todo) {
        Todo existingTodo = todoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo Not Found"));
        existingTodo.setTitle(todo.getTitle());
        existingTodo.setCompleted(todo.isCompleted());
        todoRepository.save(existingTodo);
        return new TodoResponse(
                existingTodo.getId(),
                existingTodo.getTitle(),
                existingTodo.getCompleted()
        );
    }
}
