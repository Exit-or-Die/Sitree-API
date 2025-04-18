package com.eod.sitree.project.ui.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProjectListResponseDto {
    private final int pageNo;
    private final boolean isLastPage;
    private final List<ProjectDisplayElement> projectList;

    @Getter
    public static class ProjectDisplayElement{
        private final long projectId;
        private final String name;
        private final String thumbnail;
        private final String shortDescription;
        private final String backgroundImage;
        private final long commentCount;
        private final long likesCount;
        private final long viewCount;
        private final LocalDateTime latestUpdateTime; // timestamp 형식
        private Boolean isHealthy;
        @JsonIgnore
        private final String healthCheckUrl;
        private final Boolean existFocusedOn;

        public ProjectDisplayElement(Long projectId, String name, String thumbnail, String shortDescription,
                String backgroundImage, long commentCount, long likesCount, long viewCount,
                LocalDateTime latestUpdateTime, String healthCheckUrl, Boolean existFocusedOn) {
            this.projectId = projectId;
            this.name = name;
            this.thumbnail = thumbnail;
            this.shortDescription = shortDescription;
            this.backgroundImage = backgroundImage;
            this.commentCount = commentCount;
            this.likesCount = likesCount;
            this.viewCount = viewCount;
            this.latestUpdateTime = latestUpdateTime;
            this.existFocusedOn = existFocusedOn;
            this.isHealthy = null;
            this.healthCheckUrl = healthCheckUrl;
        }

        public void setIsHealthy(boolean isHealthy) {
            this.isHealthy = isHealthy;
        }
    }
}
