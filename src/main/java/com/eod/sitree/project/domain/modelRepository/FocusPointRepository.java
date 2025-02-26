package com.eod.sitree.project.domain.modelRepository;

import com.eod.sitree.project.domain.model.FocusPoint;

public interface FocusPointRepository {

    FocusPoint saveFocusPoint(long participantId, FocusPoint focusPoint);

    FocusPoint findFocusPointsById(long focusPointId);

    FocusPoint updateFocusPointByParticipantId(long participantId, FocusPoint focusPoint);
}
