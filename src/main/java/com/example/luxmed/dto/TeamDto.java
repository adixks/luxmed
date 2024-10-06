package com.example.luxmed.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TeamDto {
    private Long id;

    @NotEmpty(message = "Team name cannot be empty")
    private String name;

    private ProjectDto project;
}
