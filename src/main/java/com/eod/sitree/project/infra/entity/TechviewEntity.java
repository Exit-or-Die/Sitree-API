package com.eod.sitree.project.infra.entity;

import com.eod.sitree.project.domain.model.Techview;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "TECHVIEW")
@NoArgsConstructor
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

    public TechviewEntity(Techview techview) {
        this.techArea = techview.getTechArea();
        this.gitRepositoryUrl = techview.getGitRepositoryUrl();
        this.architectureImageUrl = techview.getArchitectureImage().getImageUrl();
        this.architectureDescription = techview.getArchitectureDescription();
    }
}
