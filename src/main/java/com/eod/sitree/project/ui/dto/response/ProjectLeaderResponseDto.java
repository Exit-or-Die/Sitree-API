package com.eod.sitree.project.ui.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProjectLeaderResponseDto {

    private final long memberId;
    private final String imageUrl;
    private final String nickname;
    private final String position;
}
