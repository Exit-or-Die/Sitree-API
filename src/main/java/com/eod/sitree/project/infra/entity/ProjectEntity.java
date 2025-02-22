package com.eod.sitree.project.infra.entity;

import com.eod.sitree.common.infra.entity.BaseEntity;
import com.eod.sitree.project.domain.model.Architecture;
import com.eod.sitree.project.domain.model.Category;
import com.eod.sitree.project.domain.model.Head;
import com.eod.sitree.project.domain.model.Overview;
import com.eod.sitree.project.domain.model.Participant;
import com.eod.sitree.project.domain.model.Project;
import com.eod.sitree.project.domain.model.Techview;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
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

    public Project toDomainModel(List<Category> categoryList, List<Techview> techviewList, List<Architecture> architectureList, List<Participant> participantList) {
        Head head = this.headEntity.toDomainModel();
        Overview overview = this.overviewEntity.toDomainModel();
        return new Project(head, categoryList, overview, techviewList, architectureList, participantList, this.viewCount, this.getCreatedAt());
    }

    public void updateProjectEntity(ProjectEntity projectEntity) {
        this.headEntity = projectEntity.getHeadEntity();
        this.overviewEntity = projectEntity.getOverviewEntity();
    }
}
