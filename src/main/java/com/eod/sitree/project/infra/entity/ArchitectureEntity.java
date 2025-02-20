package com.eod.sitree.project.infra.entity;

import com.eod.sitree.project.domain.model.Architecture;
import com.eod.sitree.project.domain.model.Image;
import com.eod.sitree.project.domain.model.type.ArchitectureType;
import com.eod.sitree.project.domain.model.type.ImageType;
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
@Table(name = "ARCHITECTURE")
@NoArgsConstructor
public class ArchitectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long architectureId;

    @Column(nullable = false)
    private long projectId;

    @Column(nullable = false)
    private ArchitectureType architectureType;

    private String architectureDesc;

    private String architectureImageUrl;

    public ArchitectureEntity(Architecture architecture, long projectId) {
        this.projectId = projectId;
        this.architectureType = architecture.getArchitectureType();
        this.architectureDesc = architecture.getArchitectureDesc();
        this.architectureImageUrl = architecture.getArchitectureImage().getImageUrl();
    }

    public Architecture toDomainModel(){
        return new Architecture(this.architectureType, this.architectureDesc,
                new Image(this.architectureImageUrl, ImageType.ARCHITECTURE));
    }

    public void updateArchitectureEntity(Architecture architecture) {
        this.architectureType = architecture.getArchitectureType();
        this.architectureDesc = architecture.getArchitectureDesc();
        this.architectureImageUrl = architecture.getArchitectureImage().getImageUrl();
    }
}
