package com.example.taskservice.utils;

import com.example.taskservice.dto.TaskDTO;
import com.example.taskservice.models.TaskEntity;

public class TaskMapper {

    public static TaskDTO toTaskDTO(TaskEntity taskEntity) {
        TaskDTO dto = new TaskDTO();
        dto.setId(taskEntity.getId());
        dto.setTitle(taskEntity.getTitle());
        dto.setDescription(taskEntity.getDescription());
        dto.setStatus(taskEntity.getStatus());
        dto.setUserEmail(taskEntity.getUserEmail());
        return dto;
    }

    public static TaskEntity toTaskEntity(TaskDTO taskDTO) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskDTO.getId());
        taskEntity.setTitle(taskDTO.getTitle());
        taskEntity.setDescription(taskDTO.getDescription());
        taskEntity.setStatus(taskDTO.getStatus());
        taskEntity.setUserEmail(taskDTO.getUserEmail());
        return taskEntity;
    }
}
