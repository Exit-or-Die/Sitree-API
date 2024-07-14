package com.eod.sitree.project.domain.model;

import com.eod.sitree.project.domain.model.type.ImageType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Image {
    private final String imageUrl;
    private final ImageType imageType;
}
