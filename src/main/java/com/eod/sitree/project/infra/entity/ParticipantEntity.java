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
    private Long memberId;

    @Column(nullable = false)
    private Long projectId;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private boolean isLeader;

    public ParticipantEntity(Long projectId, Participant participant) {
        this.memberId = participant.getMemberId();
        this.projectId = projectId;
        this.position = participant.getPosition();
        this.isLeader = participant.isLeader();
    }

    public Participant toDomainEntity() {
        return new Participant(this.memberId, this.position, this.isLeader);
    }
}
