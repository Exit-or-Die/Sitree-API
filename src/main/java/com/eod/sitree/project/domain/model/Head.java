package com.eod.sitree.project.domain.model;

import static com.eod.sitree.common.exception.ApplicationErrorType.CHECK_HEAD_REQUIRED_VALUE;

import com.eod.sitree.project.exeption.ProjectException;
import com.eod.sitree.project.infra.entity.HeadEntity;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto.HeadDto;
import lombok.Getter;

@Getter
public class Head {
    private String thumbnailImageUrl;
    private String title;
    private String shortDescription;
    private String healthCheckUrl;

    public Head(String thumbnailImageUrl, String title, String healthCheckUrl) {
        validation(thumbnailImageUrl, title, healthCheckUrl);
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.title = title;
        this.healthCheckUrl = healthCheckUrl;
    }

    public Head(String thumbnailImageUrl, String title, String shortDescription, String healthCheckUrl) {
        validation(thumbnailImageUrl, title, healthCheckUrl);
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.title = title;
        this.shortDescription = shortDescription;
        this.healthCheckUrl = healthCheckUrl;
    }

    public Head(HeadDto dto) { // NotNull verified dto
        this.thumbnailImageUrl = dto.getThumbnailImageUrl();
        this.title = dto.getTitle();
        this.shortDescription = dto.getShortDescription();
        this.healthCheckUrl = dto.getHealthCheckUrl();
    }

    public void changeThumbnailUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void changeHealthCheckUrl(String healthCheckUrl) {
        this.healthCheckUrl = healthCheckUrl;
    }

    public void validation(String thumbnailImageUrl, String title, String healthCheckUrl) {
        if (thumbnailImageUrl == null || title == null || healthCheckUrl == null) {
            throw new ProjectException(CHECK_HEAD_REQUIRED_VALUE);
        }
    }
}
