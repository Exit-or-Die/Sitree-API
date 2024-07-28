package com.eod.sitree.project.domain.model;

import com.eod.sitree.project.domain.model.type.ImageType;
import com.eod.sitree.project.ui.dto.request.ProjectCreateRequestDto.ImageDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Image {
    private final String imageUrl;
    private final ImageType imageType;

    public Image() {
        imageUrl = null;
        imageType = null;
    }

    public Image(ImageDto dto) {
        this.imageUrl = dto.getImageUrl();
        this.imageType = dto.getImageType();
    }
}
