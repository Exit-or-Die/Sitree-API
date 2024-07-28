package com.eod.sitree.project.domain.model;

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
}
