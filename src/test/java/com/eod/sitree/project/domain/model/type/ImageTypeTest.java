package com.eod.sitree.project.domain.model.type;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ImageTypeTest {

    @Test
    void isBackgroundImage() {
        ImageType backgroundImage = ImageType.BACKGROUND;
        assertThat(backgroundImage.isBackgroundImage()).isEqualTo(true);
        assertThat(backgroundImage.isDetailImage()).isEqualTo(false);
    }

    @Test
    void isDetailImage() {
        ImageType detailImage = ImageType.DETAIL;
        assertThat(detailImage.isBackgroundImage()).isEqualTo(false);
        assertThat(detailImage.isDetailImage()).isEqualTo(true);
    }
}