package com.example.luxmed.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ProjectDto {
    private Long id;

    @NotEmpty(message = "Project name cannot be empty")
    private String name;

    private ManagerDto manager;
}
