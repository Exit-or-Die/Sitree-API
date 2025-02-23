package com.eod.sitree.project.infra.entity;

import com.eod.sitree.common.converter.StringListConverter;
import com.eod.sitree.project.domain.model.FocusPoint;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
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

    @Convert(converter = StringListConverter.class)
    private List<String> focusPoints;

    public FocusPointEntity(Long memberId, Long participantId, List<String> focusPoints) {
        this.memberId = memberId;
        this.participantId = participantId;
        this.focusPoints = focusPoints;
    }

    public FocusPoint toDomainModel() {
        return new FocusPoint(this.memberId, this.focusPoints);
    }

    public void updateFocusPoints(List<String> focusPoints) {
        this.focusPoints = focusPoints;
    }
}
