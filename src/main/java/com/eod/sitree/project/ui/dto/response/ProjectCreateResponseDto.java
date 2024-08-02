package com.eod.sitree.project.ui.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ProjectCreateResponseDto {
    private String detailUrlPath;

    public ProjectCreateResponseDto(long projectId) {
        this.detailUrlPath = "/project/" + projectId;
    }
}
