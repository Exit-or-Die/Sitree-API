package com.eod.sitree.project.ui.dto.response;

import lombok.Getter;

@Getter
public class ProjectCreateResponseDto {
    private final String detailUrlPath;

    public ProjectCreateResponseDto(long projectId) {
        this.detailUrlPath = "/project/" + projectId;
    }
}
