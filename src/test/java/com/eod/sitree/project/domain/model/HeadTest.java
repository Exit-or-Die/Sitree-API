package com.eod.sitree.project.domain.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.eod.sitree.project.exeption.ProjectException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeadTest {

    private Head head;
    private final String thumbnailImageUrl = "thumbnail Image Url";
    private final String title = "title";
    private final String shortDescription = "short description";

    @BeforeEach
    void init() {
        head = new Head(thumbnailImageUrl, title, shortDescription);
    }

    @Test
    void createHead() {
        assertThat(head).isNotNull();
    }

    @Test
    void createHeadWithoutTitle() {
        Assertions.assertThrows(ProjectException.class, () -> {
            new Head(thumbnailImageUrl, null);
        });
    }

    @Test
    void createHeadWithoutThumbnailUrl() {
        Assertions.assertThrows(ProjectException.class, () -> {
            new Head(null, title);
        });
    }

    @Test
    void createHeadError() {
        Assertions.assertThrows(ProjectException.class, () -> {
            new Head(null, null, shortDescription);
        });
    }

    @Test
    void changeThumbnailUrl() {
        String changedThumbnailUrl = "changed Thumbnail Image Url";
        head.changeThumbnailUrl(changedThumbnailUrl);
        assertThat(head.getThumbnailImageUrl()).isEqualTo(changedThumbnailUrl);
    }

    @Test
    void changeTitle() {
        String changedTitle = "changed title";
        head.changeTitle(changedTitle);
        assertThat(head.getTitle()).isEqualTo(changedTitle);

    }

    @Test
    void changeShortDescription() {
        String changedShortDescription = "changed short Description";
        head.changeShortDescription(changedShortDescription);
        assertThat(head.getShortDescription()).isEqualTo(changedShortDescription);
    }
}