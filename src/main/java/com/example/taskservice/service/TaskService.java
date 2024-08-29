package com.example.taskservice.service;

import com.example.taskservice.dto.TaskDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {

    Mono<TaskDTO> getTaskById(Long id);

    Flux<TaskDTO> getAllTasks();

    Mono<TaskDTO> createTask(TaskDTO taskDTO);

    Mono<TaskDTO> updateTask(Long id, TaskDTO taskDTO);

    Mono<Void> deleteTask(Long id);

    Flux<TaskDTO> findByUserEmail(String email);
}
