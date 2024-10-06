package com.example.luxmed.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CompanyDto {
    private Long id;

    @NotEmpty(message = "Company name cannot be empty")
    private String name;

    private List<DepartmentDto> departments;
}
