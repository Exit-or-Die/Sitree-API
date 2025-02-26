package com.eod.sitree.project.infra;

import com.eod.sitree.common.exception.ApplicationErrorType;
import com.eod.sitree.project.domain.model.FocusPoint;
import com.eod.sitree.project.domain.modelRepository.FocusPointRepository;
import com.eod.sitree.project.exeption.ProjectException;
import com.eod.sitree.project.infra.entity.FocusPointEntity;
import com.eod.sitree.project.infra.jpa_interfaces.FocusPointJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FocusPointRepositoryImpl implements FocusPointRepository {

    private final FocusPointJpaRepository focusPointJpaRepository;


    @Override
    public FocusPoint saveFocusPoint(long participantId, FocusPoint focusPoint) {
        FocusPointEntity entity = new FocusPointEntity(focusPoint.getMemberId(), participantId,
                focusPoint.getFocusPoints());
        focusPointJpaRepository.save(entity);
        return focusPoint;
    }

    @Override
    public FocusPoint findFocusPointsById(long focusPointId) {
        FocusPointEntity focusPointEntity = focusPointJpaRepository.findByFocusPointId(focusPointId)
                .orElseThrow(() ->  new ProjectException(
                ApplicationErrorType.NOT_EXIST_FOCUS_POINT_ID));
        return focusPointEntity.toDomainModel();
    }

    @Override
    public FocusPoint updateFocusPointByParticipantId(long participantId, FocusPoint focusPoint) {
        FocusPointEntity focusPointEntity = focusPointJpaRepository.findByParticipantId(participantId)
                .orElseThrow(
                        () -> new ProjectException(ApplicationErrorType.NOT_EXIST_FOCUS_POINT_ID));

        focusPointEntity.updateFocusPoints(focusPoint.getFocusPoints());
        return focusPoint;
    }
}
