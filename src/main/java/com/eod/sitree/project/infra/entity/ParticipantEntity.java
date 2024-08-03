package com.eod.sitree.project.infra.entity;

import com.eod.sitree.project.domain.model.Participant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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

    @Column(nullable = false)
    private String position;

    public ParticipantEntity(Long projectId, Participant participant) {
        this.memberNo = participant.getMemberNo();
        this.projectId = projectId;
        this.position = participant.getPosition();
    }
}
