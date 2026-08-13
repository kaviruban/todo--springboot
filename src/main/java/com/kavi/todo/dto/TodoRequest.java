package com.kavi.todo.dto;

import jakarta.validation.constraints.NotBlank;

public class TodoRequest {
    @NotBlank
    private String title;

    
    private boolean completed;

    public TodoRequest() {

    }

    public TodoRequest(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
