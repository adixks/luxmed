package com.example.luxmed.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentDto {
    private Long id;

    @NotEmpty(message = "Department name cannot be empty")
    private String name;

    private List<TeamDto> teams;
}
