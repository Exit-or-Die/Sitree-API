package com.eod.sitree.project.infra.entity;

import com.eod.sitree.project.domain.model.FocusPoint;
import com.eod.sitree.project.domain.model.Image;
import com.eod.sitree.project.domain.model.Techview;
import com.eod.sitree.project.domain.model.type.ImageType;
import com.eod.sitree.project.domain.model.type.TechStackType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
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
    private long projectId;

    @Column(nullable = false)
    private String techArea;

    @Column(nullable = false)
    private String gitRepositoryUrl;

    @Column(nullable = false)
    private String architectureImageUrl;

    private String architectureDescription;

    public TechviewEntity(Long projectId, Techview techview) {
        this.techArea = techview.getTechArea();
        this.projectId = projectId;
        this.gitRepositoryUrl = techview.getGitRepositoryUrl();
        this.architectureImageUrl = techview.getArchitectureImage().getImageUrl();
        this.architectureDescription = techview.getArchitectureDescription();
    }

    public Techview toDomainModel(List<TechStackType> projectTechStacks, List<FocusPoint> focusPoints){
        return new Techview(this.techArea, this.gitRepositoryUrl, projectTechStacks,
                new Image(this.architectureImageUrl, ImageType.ARCHITECTURE), this.architectureDescription, focusPoints);
    }
}
