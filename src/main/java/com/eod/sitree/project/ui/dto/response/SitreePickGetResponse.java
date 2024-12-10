package com.eod.sitree.project.ui.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SitreePickGetResponse {
    private final long projectId;
    private final String name;
    private final String thumbnail;
    private final String backgroundImage;
    private final long commentCount;
    private final long likesCount;
    private final long viewCount;
}
