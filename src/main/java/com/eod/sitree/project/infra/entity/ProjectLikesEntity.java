package com.eod.sitree.project.infra.entity;

import jakarta.persistence.Column;
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
@Table(name = "LIKES")
public class ProjectLikesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long likesId;

    @Column(name = "project_id")
    private Long projectId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isLiked;

    public ProjectLikesEntity(long projectId, long memberId) {
        this.likesId = null;
        this.projectId = projectId;
        this.memberId = memberId;
        this.isLiked = false;
    }

    public boolean toggleLike(){
        this.isLiked = !this.isLiked;
        return this.isLiked;
    }
}
