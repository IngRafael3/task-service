package com.example.taskservice.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("tasks")
public class TaskEntity {
    @Id
    private Long id;

    private String title;

    private String description;

    private String status;

    @Column(value = "user_email")
    private String userEmail; // Field for the user ID

    public TaskEntity(String title, String description, String status, String userEmail) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.userEmail = userEmail;
    }

    public TaskEntity() {
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
