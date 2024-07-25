package com.eod.sitree.project.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TECHVIEW")
public class TechviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long techviewId;

    @Column(nullable = false)
    private String techArea;

    @Column(nullable = false)
    private String gitRepositoryUrl;

    @Column(nullable = false)
    private String architectureImageUrl;

    private String architectureDescription;
}
