package com.eod.sitree.project.ui.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class ParticipatedProjectsResponseDto {
    private final long projectId;
    private final String name;
    private final String thumbnail;
    private final String shortDescription;
    private final String backgroundImage;
    private final long commentCount;
    private final long likesCount;
    private final long viewCount;
    private final LocalDateTime latestUpdateTime;
    private Boolean isHealthy;
    @JsonIgnore
    private final String healthCheckUrl;
    private final Long participantId;
    private final FocusPoint focusPoint;

    public ParticipatedProjectsResponseDto(long projectId, String name, String thumbnail,
            String shortDescription, String backgroundImage, long commentCount, long likesCount,
            long viewCount, LocalDateTime latestUpdateTime, String healthCheckUrl, long focusPointId, List<String> focusPoint, Long participantId) {
        this.projectId = projectId;
        this.name = name;
        this.thumbnail = thumbnail;
        this.shortDescription = shortDescription;
        this.backgroundImage = backgroundImage;
        this.commentCount = commentCount;
        this.likesCount = likesCount;
        this.viewCount = viewCount;
        this.latestUpdateTime = latestUpdateTime;
        this.healthCheckUrl = healthCheckUrl;
        this.participantId = participantId;
        this.focusPoint = focusPoint == null ? null : new FocusPoint(focusPointId, focusPoint); // 작성된 focusPoint 없으면 null 반환
    }

    @Getter
    @RequiredArgsConstructor
    private static class FocusPoint {
        private final long focusPointId;
        private final List<String> focusPoints;
    }

    public void setIsHealthy(boolean isHealthy) {
        this.isHealthy = isHealthy;
    }
}
