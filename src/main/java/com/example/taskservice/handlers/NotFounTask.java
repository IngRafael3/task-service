package com.example.taskservice.handlers;

public class NotFounTask extends RuntimeException {
    public NotFounTask(String message) {
        super(message);
    }
}
