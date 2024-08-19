package com.eod.sitree.project.domain.model;

import com.eod.sitree.project.infra.entity.FocusPointEntity;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto.TechviewDto.FocusPointDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FocusPoint {
    private final long memberNo;
    private final String focusedOn;

    public FocusPoint(FocusPointDto dto) {
        this.memberNo = dto.getMemberNo();
        this.focusedOn = dto.getFocusedOn();
    }

    public FocusPoint(FocusPointEntity entity) {
        this.memberNo = entity.getMemberNo();
        this.focusedOn = entity.getFocusedOn();
    }
}
