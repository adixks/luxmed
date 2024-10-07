package com.example.luxmed.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manager_id", nullable = true)
    private Manager manager;
}
