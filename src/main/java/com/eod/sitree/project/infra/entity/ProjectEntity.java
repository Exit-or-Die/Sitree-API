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
import org.hibernate.annotations.ColumnDefault;

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
    private HeadEntity headEntity;

    @Embedded
    private OverviewEntity overviewEntity;

    @Column(nullable = false)
    @ColumnDefault("0")
    private long viewCount;

    public ProjectEntity(Project project) {
        this.headEntity = new HeadEntity(project.getHead());
        this.overviewEntity = new OverviewEntity(project.getOverview());
    }
}
