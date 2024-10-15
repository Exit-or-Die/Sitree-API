package com.eod.sitree.project.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Participant {
    private final Long memberId;
    private final String position;
    private final boolean isLeader;
    private final FocusPoint focusPoint;

    public boolean isEmptyFocusPoint(){
        return focusPoint == null || focusPoint.getMemberId() == 0
                || focusPoint.getFocusedOn() == null;
    }
}
