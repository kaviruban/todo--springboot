package com.kavi.todo.repository;

import com.kavi.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo , Long> {
}
