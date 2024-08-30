package com.example.taskservice.dto;

import java.util.Objects;

public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String userEmail; // User ID field


    public TaskDTO(String title, String description, String status, String userEmail) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.userEmail = userEmail;
    }

    public TaskDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return Objects.equals(title, taskDTO.title) &&
                Objects.equals(description, taskDTO.description) &&
                Objects.equals(status, taskDTO.status) &&
                Objects.equals(userEmail, taskDTO.userEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, status, userEmail);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
