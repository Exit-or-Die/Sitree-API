package com.eod.sitree.project.infra.entity;

import com.eod.sitree.project.domain.model.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TAG")
@NoArgsConstructor
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long tagId;

    @Column(unique = true, nullable = false)
    private String name;

    private Long projectId;

    public TagEntity(Long projectId, Tag tag) {
        this.name = tag.getName();
        this.projectId = projectId;
    }
}
