package com.example.taskservice.repository;

import com.example.taskservice.models.TaskEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TaskRepository extends ReactiveCrudRepository<TaskEntity, Long> {
}
