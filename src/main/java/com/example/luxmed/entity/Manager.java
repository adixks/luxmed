package com.example.luxmed.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String phone;
}
