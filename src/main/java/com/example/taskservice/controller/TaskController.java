package com.example.taskservice.controller;

import com.example.taskservice.dto.TaskDTO;
import com.example.taskservice.handlers.NotFounTask;
import com.example.taskservice.models.TaskEntity;
import com.example.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public Mono<TaskDTO> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .switchIfEmpty(Mono.error(new NotFounTask("Task no found with id: "+id)));
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
                .switchIfEmpty(Mono.error(new NotFounTask("Task no found with id: "+id)));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTask(@PathVariable Long id) {
        return taskService.deleteTask(id)
                .switchIfEmpty(Mono.error(new NotFounTask("Task no found with id: "+id)));
    }

    @GetMapping("/user/{email}")
    public Flux<TaskDTO> getByUser(@PathVariable String email){
        return taskService.findByUserEmail(email).delayElements(Duration.ofSeconds(1));
    }
}

