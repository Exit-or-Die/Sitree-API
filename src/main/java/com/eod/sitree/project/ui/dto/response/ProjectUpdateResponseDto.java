package com.eod.sitree.project.ui.dto.response;

import lombok.Getter;

@Getter
public class ProjectUpdateResponseDto {
    private final String detailUrlPath;

    public ProjectUpdateResponseDto(long projectId) {
        this.detailUrlPath = "/project/" + projectId;
    }
}
