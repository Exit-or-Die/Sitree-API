package com.eod.sitree.project.infra.entity;

import com.eod.sitree.project.domain.model.Techview;
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
    private String techTitle;

    @Column(nullable = false)
    private String gitRepositoryUrl;

    private String techDesc;

    public TechviewEntity(Long projectId, Techview techview) {
        this.techTitle = techview.getTechTitle();
        this.projectId = projectId;
        this.gitRepositoryUrl = techview.getGitRepositoryUrl();
        this.techDesc = techview.getTechDesc();
    }

    public Techview toDomainModel(List<TechStackType> projectTechStacks){
        return new Techview(this.techTitle, this.gitRepositoryUrl, projectTechStacks, this.techDesc);
    }
}
