package com.eod.sitree.project.domain.model;

import static com.eod.sitree.common.exception.ApplicationErrorType.CHECK_HEAD_REQUIRED_VALUE;

import com.eod.sitree.project.exeption.ProjectException;
import lombok.Getter;

@Getter
public class Head {
    private String thumbnailImageUrl;
    private String title;
    private String shortDescription;

    public Head(String thumbnailImageUrl, String title) {
        validation(thumbnailImageUrl, title);
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.title = title;
    }

    public Head(String thumbnailImageUrl, String title, String shortDescription) {
        validation(thumbnailImageUrl, title);
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.title = title;
        this.shortDescription = shortDescription;
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

    public void validation(String thumbnailImageUrl, String title) {
        if (thumbnailImageUrl == null || title == null) {
            throw new ProjectException(CHECK_HEAD_REQUIRED_VALUE);
        }
    }
}
