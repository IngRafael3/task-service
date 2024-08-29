package com.example.taskservice.controller;

import com.example.taskservice.dto.TaskDTO;
import com.example.taskservice.models.TaskEntity;
import com.example.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public Mono<TaskDTO> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .switchIfEmpty(Mono.error(new TaskNotFoundException(id)));
    }

    @GetMapping
    public Flux<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Mono<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @PutMapping("/{id}")
    public Mono<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return taskService.updateTask(id, taskDTO)
                .switchIfEmpty(Mono.error(new TaskNotFoundException(id)));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id)
                .switchIfEmpty(Mono.error(new TaskNotFoundException(id)));
    }
}

class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task with ID " + id + " not found");
    }
}
