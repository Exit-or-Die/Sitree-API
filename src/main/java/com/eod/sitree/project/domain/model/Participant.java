package com.eod.sitree.project.domain.model;

import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto.ParticipantDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Participant {
    private final Long memberNo;
    private final String position;

    public Participant(ParticipantDto dto) {
        this.memberNo = dto.getMemberNo();
        this.position = dto.getPosition();
    }
}
