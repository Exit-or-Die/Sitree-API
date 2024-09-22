package com.eod.sitree.project.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "CATEGORY_USAGE")
@NoArgsConstructor
public class CategoryUsageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long categoryUsageId;

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private Long projectId;

    public CategoryUsageEntity(Long projectId, Long categoryId) {
        this.categoryId = categoryId;
        this.projectId = projectId;
    }
}
