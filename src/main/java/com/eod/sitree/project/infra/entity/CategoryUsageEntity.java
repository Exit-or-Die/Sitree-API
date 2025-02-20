package com.eod.sitree.project.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "CATEGORY_USAGE")
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryUsageEntity categoryUsageEntity)) return false;
        return Objects.equals(categoryUsageId, categoryUsageEntity.categoryUsageId) &&
                Objects.equals(categoryId, categoryUsageEntity.categoryId)&&
                Objects.equals(projectId, categoryUsageEntity.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryUsageId, categoryId, projectId);
    }
}
