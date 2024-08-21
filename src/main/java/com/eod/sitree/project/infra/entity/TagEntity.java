package com.eod.sitree.project.infra.entity;

import com.eod.sitree.project.domain.model.Tag;
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
@Table(name = "TAG")
@NoArgsConstructor
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long tagId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long projectId;

    public TagEntity(Long projectId, Tag tag) {
        this.name = tag.getName();
        this.projectId = projectId;
    }
}