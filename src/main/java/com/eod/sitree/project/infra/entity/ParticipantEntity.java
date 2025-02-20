package com.eod.sitree.project.infra.entity;

import com.eod.sitree.project.domain.model.Participant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
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

    public void updateParticipantEntity(Participant participant) {
        this.position = participant.getPosition();
        this.isLeader = participant.isLeader();
    }

    @Override
    public boolean equals(Object o) { // 같은 참여자인지를 확인하기 위해 equals 새로 지정. (memberId, projectId)
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantEntity that = (ParticipantEntity) o;
        return Objects.equals(memberId, that.memberId) &&
                Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, projectId);
    }
}
