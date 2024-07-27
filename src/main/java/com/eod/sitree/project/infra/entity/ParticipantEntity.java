package com.eod.sitree.project.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PARTICIPANT")
@NoArgsConstructor
public class ParticipantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long participantId;

    @Column(nullable = false)
    private Long memberNo;

    @Column(nullable = false)
    private Long projectId;

    public ParticipantEntity(Long projectId, Long memberNo) {
        this.memberNo = memberNo;
        this.projectId = projectId;
    }
}
