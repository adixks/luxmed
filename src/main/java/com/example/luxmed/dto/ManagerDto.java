package com.example.luxmed.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ManagerDto {
    private Long id;

    @NotEmpty(message = "Manager name cannot be empty")
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    private String phone;
}
