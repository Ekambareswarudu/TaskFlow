package com.infy.TaskFlow.dto;

import com.infy.TaskFlow.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private LocalDate dueDate;

    private TaskStatus status = TaskStatus.TODO;
}