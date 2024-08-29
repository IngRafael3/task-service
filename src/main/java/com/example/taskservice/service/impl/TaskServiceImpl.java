package com.example.taskservice.service.impl;

import com.example.taskservice.dto.TaskDTO;
import com.example.taskservice.models.TaskEntity;
import com.example.taskservice.repository.TaskRepository;
import com.example.taskservice.service.TaskService;
import com.example.taskservice.utils.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Mono<TaskDTO> getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(TaskMapper::toTaskDTO);
    }

    @Override
    public Flux<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .map(TaskMapper::toTaskDTO);
    }

    @Override
    public Mono<TaskDTO> createTask(TaskDTO taskDTO) {
        TaskEntity taskEntity = TaskMapper.toTaskEntity(taskDTO);
        return taskRepository.save(taskEntity)
                .map(TaskMapper::toTaskDTO);
    }

    @Override
    public Mono<TaskDTO> updateTask(Long id, TaskDTO taskDTO) {
        return taskRepository.findById(id)
                .flatMap(existingTask -> {
                    existingTask.setTitle(taskDTO.getTitle());
                    existingTask.setDescription(taskDTO.getDescription());
                    existingTask.setStatus(taskDTO.getStatus());
                    existingTask.setUserId(taskDTO.getUserId()); // Update userId
                    return taskRepository.save(existingTask);
                })
                .map(TaskMapper::toTaskDTO);
    }

    @Override
    public Mono<Void> deleteTask(Long id) {
        return taskRepository.findById(id)
                .flatMap(taskRepository::delete);
    }

}
