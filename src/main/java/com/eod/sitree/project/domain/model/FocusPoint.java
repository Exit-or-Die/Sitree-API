package com.eod.sitree.project.domain.model;

import com.eod.sitree.project.infra.entity.FocusPointEntity;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto.TechviewDto.FocusPointDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FocusPoint {
    private final long memberId;
    private final String focusedOn;

    public FocusPoint(FocusPointDto dto) {
        this.memberId = dto.getMemberId();
        this.focusedOn = dto.getFocusedOn();
    }

    public FocusPoint(FocusPointEntity entity) {
        this.memberId = entity.getMemberId();
        this.focusedOn = entity.getFocusedOn();
    }
}
