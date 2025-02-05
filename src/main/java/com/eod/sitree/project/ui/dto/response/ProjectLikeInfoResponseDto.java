package com.eod.sitree.project.ui.dto.response;

import lombok.Getter;

@Getter
public class ProjectLikeInfoResponseDto {

    private final Long likeCount;
    private final Boolean isLiked;

    public ProjectLikeInfoResponseDto(Long likeCount, Boolean isLiked) {
        this.likeCount = likeCount;
        this.isLiked = isLiked;
    }
}
