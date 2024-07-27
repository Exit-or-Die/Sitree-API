package com.eod.sitree.project.infra.entity;

import com.eod.sitree.common.infra.entity.BaseEntity;
import com.eod.sitree.project.domain.model.Project;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROJECT")
public class ProjectEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long projectId;

    @Embedded
    private HeadEntity head;

    @Embedded
    private OverviewEntity overview;

    public ProjectEntity(Project project) {
        this.head = new HeadEntity(project.getHead());
        this.overview = new OverviewEntity(project.getOverview());
    }
}
