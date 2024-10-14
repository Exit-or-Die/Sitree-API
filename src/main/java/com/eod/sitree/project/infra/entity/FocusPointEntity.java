package com.eod.sitree.project.infra.entity;

import com.eod.sitree.project.domain.model.FocusPoint;
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
@Table(name = "FOCUS_POINT")
@NoArgsConstructor
public class FocusPointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long focusPointId;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long participantId;

    @Column(nullable = false)
    private String focusedOn;

    public FocusPoint toDomainModel() {
        return new FocusPoint(this.memberId, this.focusedOn);
    }
}
