package com.eod.sitree.project.infra.entity;

import com.eod.sitree.project.domain.model.type.TechStackType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROJECT_TECH_STACK")
public class ProjectTechStackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long projectTechStackId;

    @Column(nullable = false)
    private Long techviewId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TechStackType techStackType;
}
