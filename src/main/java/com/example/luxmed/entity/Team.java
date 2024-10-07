package com.example.luxmed.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", nullable = true)
    private Project project;
}
