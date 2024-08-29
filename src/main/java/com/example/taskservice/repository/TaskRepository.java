package com.example.taskservice.repository;

import com.example.taskservice.dto.TaskDTO;
import com.example.taskservice.models.TaskEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TaskRepository extends ReactiveCrudRepository<TaskEntity, Long> {

    Flux<TaskDTO> findByUserEmail(String email);
}
