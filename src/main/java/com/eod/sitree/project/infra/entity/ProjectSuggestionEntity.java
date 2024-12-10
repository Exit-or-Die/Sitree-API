package com.eod.sitree.project.infra.entity;

import com.eod.sitree.common.infra.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "PROJECT_SUGGESTION")
@NoArgsConstructor
public class ProjectSuggestionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long projectSuggestionId;

    @Column(nullable = false)
    private Long projectId;

    @Column(nullable = false)
    private String suggestTopic;
}
